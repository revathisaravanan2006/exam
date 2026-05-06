package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Participation;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.ParticipationRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServices {

    @Autowired
    private ParticipationRepo participationRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DebateRepo debateRepo;

    public Participation createParticipation(int userId, int debateId, String speakingRole) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        Participation participation = new Participation();
        participation.setUser(user);
        participation.setDebate(debate);
        participation.setSpeakingRole(speakingRole);
        return participationRepo.save(participation);
    }

    public List<Participation> getAllParticipations() {
        return participationRepo.findAll();
    }

    public Participation getParticipationById(int id) {
        return participationRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Participation not found: " + id));
    }

    public Participation updateParticipation(int id, int userId, int debateId, String speakingRole) {
        Participation existing = participationRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found: " + id));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Debate debate = debateRepo.findById(debateId)
                .orElseThrow(() -> new IllegalArgumentException("Debate not found: " + debateId));

        existing.setUser(user);
        existing.setDebate(debate);
        existing.setSpeakingRole(speakingRole);
        return participationRepo.save(existing);
    }

    public void deleteParticipation(int id) {
        participationRepo.deleteById(id);
    }
}