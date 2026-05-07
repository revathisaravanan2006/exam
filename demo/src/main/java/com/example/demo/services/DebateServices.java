package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.repositories.DebateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebateServices {

    @Autowired
    private DebateRepo debateRepo;

    public Debate createDebate(Debate debate) {
        return debateRepo.save(debate);
    }

    public List<Debate> getAllDebates() {
        return debateRepo.findAll();
    }

    public Debate getDebateById(int id) {
        return debateRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found"));
    }

    public Debate updateDebate(int id, Debate debate) {

        Debate existing = debateRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found"));

        existing.setTopic(debate.getTopic());
        existing.setDate(debate.getDate());
        existing.setTime(debate.getTime());

        return debateRepo.save(existing);
    }

    public void deleteDebate(int id) {
        debateRepo.deleteById(id);
    }
}