package com.example.demo.controller;

import com.example.demo.entities.Score;
import com.example.demo.services.ScoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreServices scoreServices;

    @PostMapping
    public Score createScore(@RequestBody Map<String, Object> request) {
        return scoreServices.createScore(
                ((Number) request.get("userId")).intValue(),
                ((Number) request.get("debateId")).intValue(),
                ((Number) request.get("judgeScore")).doubleValue()
        );
    }

    @GetMapping("/all")
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

    @PutMapping("/update/{id}")
    public Score updateScore(
            @PathVariable int id,
            @RequestBody Map<String, Object> request) {

        return scoreServices.updateScore(
                id,
                ((Number) request.get("totalScore")).doubleValue()
        );
    }

    @DeleteMapping("/delete/{id}")
    public void deleteScore(@PathVariable int id) {
        scoreServices.deleteScore(id);
    }
}