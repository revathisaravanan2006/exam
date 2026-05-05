package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.ScoreRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServices {

    private final ScoreRepo scoreRepo;
    private final UserRepo userRepo;
    private final DebateRepo debateRepo;

    public ScoreServices(ScoreRepo scoreRepo, UserRepo userRepo, DebateRepo debateRepo) {
        this.scoreRepo = scoreRepo;
        this.userRepo = userRepo;
        this.debateRepo = debateRepo;
    }

    public Score createScore(int userId, int debateId, double totalScore) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        return scoreRepo.findByUserAndDebate(user, debate)
                .map(existingScore -> {
                    existingScore.setTotalScore(totalScore);
                    return scoreRepo.save(existingScore);
                })
                .orElseGet(() -> {
                    Score score = new Score();
                    score.setUser(user);
                    score.setDebate(debate);
                    score.setTotalScore(totalScore);
                    return scoreRepo.save(score);
                });
    }

    public List<Score> getAllScores() {
        return scoreRepo.findAll();
    }

    public Score getScoreById(int scoreId) {
        return scoreRepo.findById(scoreId)
                .orElseThrow(() -> new IllegalArgumentException("Score not found: " + scoreId));
    }

    public List<Score> getScoresByDebate(int debateId) {
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));
        return scoreRepo.findByDebate(debate);
    }

    public List<Score> getScoresByUser(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return scoreRepo.findByUser(user);
    }

    public void deleteScore(int scoreId) {
        scoreRepo.deleteById(scoreId);
    }
}
