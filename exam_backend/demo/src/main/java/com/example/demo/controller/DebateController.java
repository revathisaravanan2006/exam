package com.example.demo.controller;

import com.example.demo.entities.Debate;
import com.example.demo.services.DebateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/debates")
public class DebateController {

    @Autowired
    private DebateServices debateServices;

    @PostMapping
    public Debate createDebate(@RequestBody Map<String, Object> request) {
        return debateServices.createDebate(
                ((Number) request.get("userId")).intValue(),
                (String) request.get("topic"),
                (String) request.get("date"),
                (String) request.get("time")
        );
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
        return debateServices.updateDebate(id, debate);
    }

    @DeleteMapping("/{id}")
    public void deleteDebate(@PathVariable int id) {
        debateServices.deleteDebate(id);
    }
}