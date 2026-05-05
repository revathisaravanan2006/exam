package com.example.demo.controller;

import com.example.demo.entities.Vote;
import com.example.demo.services.VoteServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    private final VoteServices voteServices;

    public VoteController(VoteServices voteServices) {
        this.voteServices = voteServices;
    }

    @PostMapping
    public Vote createVote(
            @RequestParam int userId,
            @RequestParam int debateId,
            @RequestParam int voteValue
    ) {
        return voteServices.createVote(userId, debateId, voteValue);
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

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable int id) {
        voteServices.deleteVote(id);
    }
}
