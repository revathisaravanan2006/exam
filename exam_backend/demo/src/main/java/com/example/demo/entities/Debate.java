package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Debate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int debateId;

    private String topic;
    private String date;
    private String time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}