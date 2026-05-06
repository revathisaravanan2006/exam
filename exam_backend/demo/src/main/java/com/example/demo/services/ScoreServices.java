package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.ScoreRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServices {

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DebateRepo debateRepo;

    public Score createScore(int userId, int debateId, int evaluatorId, double clarity, double logic, double relevance, double rebuttal) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));
        User evaluator = userRepo.findById(evaluatorId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluator not found: " + evaluatorId));

        double totalScore = (clarity + logic + relevance + rebuttal) / 4.0;

        return scoreRepo.findByUserAndDebateAndEvaluator(user, debate, evaluator)
                .map(existing -> {
                    existing.setClarity(clarity);
                    existing.setLogic(logic);
                    existing.setRelevance(relevance);
                    existing.setRebuttal(rebuttal);
                    existing.setTotalScore(totalScore);
                    return scoreRepo.save(existing);
                })
                .orElseGet(() -> {
                    Score score = new Score();
                    score.setUser(user);
                    score.setDebate(debate);
                    score.setEvaluator(evaluator);
                    score.setClarity(clarity);
                    score.setLogic(logic);
                    score.setRelevance(relevance);
                    score.setRebuttal(rebuttal);
                    score.setTotalScore(totalScore);
                    return scoreRepo.save(score);
                });
    }

    public List<Score> getAllScores() {
        return scoreRepo.findAll();
    }

    public Score getScoreById(int id) {
        return scoreRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Score not found: " + id));
    }

    public List<Score> getScoresByDebate(int id) {
        return scoreRepo.findByDebate_DebateId(id);
    }

    public List<Score> getScoresByUser(int id) {
        return scoreRepo.findByUser_UserId(id);
    }

    public Score updateScore(int id, int userId, int debateId, int evaluatorId, double clarity, double logic, double relevance, double rebuttal) {
        Score existing = scoreRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Score not found: " + id));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));
        User evaluator = userRepo.findById(evaluatorId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluator not found: " + evaluatorId));

        double totalScore = (clarity + logic + relevance + rebuttal) / 4.0;
        existing.setUser(user);
        existing.setDebate(debate);
        existing.setEvaluator(evaluator);
        existing.setClarity(clarity);
        existing.setLogic(logic);
        existing.setRelevance(relevance);
        existing.setRebuttal(rebuttal);
        existing.setTotalScore(totalScore);
        return scoreRepo.save(existing);
    }

    public void deleteScore(int id) {
        scoreRepo.deleteById(id);
    }
}