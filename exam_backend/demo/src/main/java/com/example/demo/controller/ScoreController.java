package com.example.demo.controller;

import com.example.demo.entities.Score;
import com.example.demo.services.ScoreServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreServices scoreServices;

    public ScoreController(ScoreServices scoreServices) {
        this.scoreServices = scoreServices;
    }

    @PostMapping
    public Score createScore(
            @RequestParam int userId,
            @RequestParam int debateId,
            @RequestParam double totalScore
    ) {
        return scoreServices.createScore(userId, debateId, totalScore);
    }

    @GetMapping
    public List<Score> getAllScores() {
        return scoreServices.getAllScores();
    }

    @GetMapping("/{id}")
    public Score getScoreById(@PathVariable int id) {
        return scoreServices.getScoreById(id);
    }

    @GetMapping("/debate/{debateId}")
    public List<Score> getScoresByDebate(@PathVariable int debateId) {
        return scoreServices.getScoresByDebate(debateId);
    }

    @GetMapping("/user/{userId}")
    public List<Score> getScoresByUser(@PathVariable int userId) {
        return scoreServices.getScoresByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteScore(@PathVariable int id) {
        scoreServices.deleteScore(id);
    }
}
