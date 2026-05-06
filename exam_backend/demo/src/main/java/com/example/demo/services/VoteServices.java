package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.repositories.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServices {

    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DebateRepo debateRepo;

    public Vote createVote(int userId, int debateId, int voteValue) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        return voteRepo.findByUserAndDebate(user, debate)
                .map(existing -> {
                    existing.setVoteValue(voteValue);
                    return voteRepo.save(existing);
                })
                .orElseGet(() -> {
                    Vote vote = new Vote();
                    vote.setUser(user);
                    vote.setDebate(debate);
                    vote.setVoteValue(voteValue);
                    return voteRepo.save(vote);
                });
    }

    public List<Vote> getAllVotes() {
        return voteRepo.findAll();
    }

    public Vote getVoteById(int id) {
        return voteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Vote not found: " + id));
    }

    public List<Vote> getVotesByDebate(int debateId) {
        return voteRepo.findByDebate_DebateId(debateId);
    }

    public Vote updateVote(int id, int userId, int debateId, int voteValue) {
        Vote existing = voteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vote not found: " + id));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        existing.setUser(user);
        existing.setDebate(debate);
        existing.setVoteValue(voteValue);
        return voteRepo.save(existing);
    }

    public void deleteVote(int id) {
        voteRepo.deleteById(id);
    }
}