package com.example.demo.controller;

import com.example.demo.entities.Debate;
import com.example.demo.services.DebateServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debates")
public class DebateController {

    @Autowired
    private DebateServices debateServices;

    @PostMapping
    public Debate createDebate(@RequestBody Debate debate) {
        return debateServices.createDebate(debate);
    }

    @GetMapping("/all")
    public List<Debate> getAllDebates() {
        return debateServices.getAllDebates();
    }

    @GetMapping("/{id}")
    public Debate getDebateById(@PathVariable int id) {
        return debateServices.getDebateById(id);
    }

    @PutMapping("/update/{id}")
    public Debate updateDebate(@PathVariable int id,
                               @RequestBody Debate debate) {

        return debateServices.updateDebate(id, debate);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDebate(@PathVariable int id) {
        debateServices.deleteDebate(id);
    }
}