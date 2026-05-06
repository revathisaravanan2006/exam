package com.example.demo.controller;

import com.example.demo.entities.Participation;
import com.example.demo.services.ParticipationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    @Autowired
    private ParticipationServices participationServices;

    @PostMapping
    public Participation createParticipation(@RequestBody Map<String, Object> request) {
        return participationServices.createParticipation(
                ((Number) request.get("userId")).intValue(),
                ((Number) request.get("debateId")).intValue(),
                (String) request.get("speakingRole")
        );
    }

    @GetMapping
    public List<Participation> getAllParticipations() {
        return participationServices.getAllParticipations();
    }

    @GetMapping("/{id}")
    public Participation getParticipationById(@PathVariable int id) {
        return participationServices.getParticipationById(id);
    }

    @PutMapping("/{id}")
    public Participation updateParticipation(@PathVariable int id, @RequestBody Map<String, Object> request) {
        return participationServices.updateParticipation(
                id,
                ((Number) request.get("userId")).intValue(),
                ((Number) request.get("debateId")).intValue(),
                (String) request.get("speakingRole")
        );
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable int id) {
        participationServices.deleteParticipation(id);
    }
}