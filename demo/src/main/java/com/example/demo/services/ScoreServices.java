package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.ScoreRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.repositories.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServices {

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private DebateRepo debateRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VoteRepo voteRepo;

    public Score createScore(int userId, int debateId, double judgeScore) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found"));

        List<Vote> votes = voteRepo.findByDebate_DebateId(debateId);

        double total = 0;

        for (Vote vote : votes) {
            total += vote.getVoteValue();
        }

        double audienceScore = votes.isEmpty() ? 0 : total / votes.size();

        double finalScore = (judgeScore * 0.7) + (audienceScore * 0.3);

        Score score = new Score();
        score.setUser(user);
        score.setDebate(debate);
        score.setJudgeScore(judgeScore);
        score.setTotalScore(finalScore);

        return scoreRepo.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepo.findAll();
    }

    public Score getScoreById(int id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Score not found"));
    }

    public List<Score> getScoresByDebate(int debateId) {
        return scoreRepo.findByDebate_DebateId(debateId);
    }

    public List<Score> getScoresByUser(int userId) {
        return scoreRepo.findByUser_UserId(userId);
    }

    public Score updateScore(int id, double totalScore) {

        Score existing = scoreRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Score not found"));

        existing.setTotalScore(totalScore);

        return scoreRepo.save(existing);
    }

    public void deleteScore(int id) {
        scoreRepo.deleteById(id);
    }
}