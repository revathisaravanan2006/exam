package com.example.demo.repositories;

import com.example.demo.entities.Debate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebateRepo extends JpaRepository<Debate, Integer> {
}