package com.example.demo.repositories;

import com.example.demo.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepo extends JpaRepository<Participation, Integer> {
}

