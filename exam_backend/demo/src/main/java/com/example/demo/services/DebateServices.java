package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebateServices {

    private final DebateRepo debateRepo;
    private final UserRepo userRepo;

    public DebateServices(DebateRepo debateRepo, UserRepo userRepo) {
        this.debateRepo = debateRepo;
        this.userRepo = userRepo;
    }

    public Debate createDebate(Debate debate, int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        debate.setUser(user);
        return debateRepo.save(debate);
    }

    public List<Debate> getAllDebates() {
        return debateRepo.findAll();
    }

    public Debate getDebateById(int debateId) {
        return debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));
    }

    public Debate updateDebate(Debate debate) {
        if (!debateRepo.existsById(debate.getDebateId())) {
            throw new IllegalArgumentException("Debate not found: " + debate.getDebateId());
        }
        return debateRepo.save(debate);
    }

    public void deleteDebate(int debateId) {
        debateRepo.deleteById(debateId);
    }
}
