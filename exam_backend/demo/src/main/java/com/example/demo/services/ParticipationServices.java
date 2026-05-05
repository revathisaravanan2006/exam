package com.example.demo.services;

import com.example.demo.entities.Debate;
import com.example.demo.entities.Participation;
import com.example.demo.entities.User;
import com.example.demo.repositories.DebateRepo;
import com.example.demo.repositories.ParticipationRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServices {

    private final ParticipationRepo participationRepo;
    private final UserRepo userRepo;
    private final DebateRepo debateRepo;

    public ParticipationServices(ParticipationRepo participationRepo, UserRepo userRepo, DebateRepo debateRepo) {
        this.participationRepo = participationRepo;
        this.userRepo = userRepo;
        this.debateRepo = debateRepo;
    }

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

    public Participation getParticipationById(int participationId) {
        return participationRepo.findById(participationId)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found: " + participationId));
    }

    public void deleteParticipation(int participationId) {
        participationRepo.deleteById(participationId);
    }
}
