package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebateServices {

    @Autowired
    private DebateRepo debateRepo;

    @Autowired
    private UserRepo userRepo;

    public Debate createDebate(int userId, String topic, String date, String time) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        Debate debate = new Debate();
        debate.setTopic(topic);
        debate.setDate(date);
        debate.setTime(time);
        debate.setUser(user);
        return debateRepo.save(debate);
    }

    public List<Debate> getAllDebates() {
        return debateRepo.findAll();
    }

    public Debate getDebateById(int id) {
        return debateRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Debate not found: " + id));
    }

    public Debate updateDebate(int id, Debate debate) {
        Debate existing = debateRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Debate not found: " + id));
        existing.setTopic(debate.getTopic());
        existing.setDate(debate.getDate());
        existing.setTime(debate.getTime());
        if (debate.getUser() != null) {
            existing.setUser(debate.getUser());
        }
        return debateRepo.save(existing);
    }

    public void deleteDebate(int id) {
        debateRepo.deleteById(id);
    }
}