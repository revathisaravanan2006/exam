package com.example.demo.repositories;

import com.example.demo.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepo extends JpaRepository<Vote, Integer> {

    List<Vote> findByDebate_DebateId(int debateId);
}