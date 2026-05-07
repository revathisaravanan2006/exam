package com.example.demo.repositories;

import com.example.demo.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Integer> {

    List<Score> findByDebate_DebateId(int debateId);

    List<Score> findByUser_UserId(int userId);
}