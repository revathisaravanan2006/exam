package com.example.demo.controller;

import com.example.demo.entities.Vote;
import com.example.demo.services.VoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteServices voteServices;

    @PostMapping
    public Vote createVote(@RequestBody Map<String, Object> request) {
        return voteServices.createVote(
                ((Number) request.get("userId")).intValue(),
                ((Number) request.get("debateId")).intValue(),
                ((Number) request.get("voteValue")).intValue()
        );
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return voteServices.getAllVotes();
    }

    @GetMapping("/{id}")
    public Vote getVoteById(@PathVariable int id) {
        return voteServices.getVoteById(id);
    }

    @GetMapping("/debate/{debateId}")
    public List<Vote> getVotesByDebate(@PathVariable int debateId) {
        return voteServices.getVotesByDebate(debateId);
    }

    @PutMapping("/{id}")
    public Vote updateVote(@PathVariable int id, @RequestBody Map<String, Object> request) {
        return voteServices.updateVote(
                id,
                ((Number) request.get("userId")).intValue(),
                ((Number) request.get("debateId")).intValue(),
                ((Number) request.get("voteValue")).intValue()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable int id) {
        voteServices.deleteVote(id);
    }
}