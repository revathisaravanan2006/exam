package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "debate_id", "evaluator_id"}))
@Data
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreId;

    private double clarity;
    private double logic;
    private double relevance;
    private double rebuttal;
    private double totalScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "debate_id")
    private Debate debate;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private User evaluator;
}