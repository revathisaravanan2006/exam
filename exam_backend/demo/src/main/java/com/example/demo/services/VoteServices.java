package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.repositories.VoteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServices {

    private final VoteRepo voteRepo;
    private final UserRepo userRepo;
    private final DebateRepo debateRepo;

    public VoteServices(VoteRepo voteRepo, UserRepo userRepo, DebateRepo debateRepo) {
        this.voteRepo = voteRepo;
        this.userRepo = userRepo;
        this.debateRepo = debateRepo;
    }

    public Vote createVote(int userId, int debateId, int voteValue) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        return voteRepo.findByUserAndDebate(user, debate)
                .map(existingVote -> {
                    existingVote.setVoteValue(voteValue);
                    return voteRepo.save(existingVote);
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

    public Vote getVoteById(int voteId) {
        return voteRepo.findById(voteId)
                .orElseThrow(() -> new IllegalArgumentException("Vote not found: " + voteId));
    }

    public List<Vote> getVotesByDebate(int debateId) {
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));
        return voteRepo.findByDebate(debate);
    }

    public void deleteVote(int voteId) {
        voteRepo.deleteById(voteId);
    }
}
