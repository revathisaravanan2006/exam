package com.example.demo.repositories;

import com.example.demo.entities.Debate;
import com.example.demo.entities.User;
import com.example.demo.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepo extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserAndDebate(User user, Debate debate);

    List<Vote> findByDebate(Debate debate);

    List<Vote> findByDebate_DebateId(int debateId);
}

