package com.example.demo.repositories;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepo extends JpaRepository<Score, Integer> {

    Optional<Score> findByUserAndDebateAndEvaluator(User user, Debate debate, User evaluator);

    List<Score> findByDebate(Debate debate);

    List<Score> findByUser(User user);

    List<Score> findByDebate_DebateId(int debateId);

    List<Score> findByUser_UserId(int userId);
}

