package com.example.demo.controller;

import com.example.demo.entities.Participation;
import com.example.demo.services.ParticipationServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationServices participationServices;

    public ParticipationController(ParticipationServices participationServices) {
        this.participationServices = participationServices;
    }

    @PostMapping
    public Participation createParticipation(
            @RequestParam int userId,
            @RequestParam int debateId,
            @RequestParam String speakingRole
    ) {
        return participationServices.createParticipation(userId, debateId, speakingRole);
    }

    @GetMapping
    public List<Participation> getAllParticipations() {
        return participationServices.getAllParticipations();
    }

    @GetMapping("/{id}")
    public Participation getParticipationById(@PathVariable int id) {
        return participationServices.getParticipationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable int id) {
        participationServices.deleteParticipation(id);
    }
}
