package com.example.demo.controller;

import com.example.demo.entities.Debate;
import com.example.demo.services.DebateServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debates")
public class DebateController {

    private final DebateServices debateServices;

    public DebateController(DebateServices debateServices) {
        this.debateServices = debateServices;
    }

    @PostMapping
    public Debate createDebate(@RequestParam int userId, @RequestBody Debate debate) {
        return debateServices.createDebate(debate, userId);
    }

    @GetMapping
    public List<Debate> getAllDebates() {
        return debateServices.getAllDebates();
    }

    @GetMapping("/{id}")
    public Debate getDebateById(@PathVariable int id) {
        return debateServices.getDebateById(id);
    }

    @PutMapping("/{id}")
    public Debate updateDebate(@PathVariable int id, @RequestBody Debate debate) {
        debate.setDebateId(id);
        return debateServices.updateDebate(debate);
    }

    @DeleteMapping("/{id}")
    public void deleteDebate(@PathVariable int id) {
        debateServices.deleteDebate(id);
    }
    
}
