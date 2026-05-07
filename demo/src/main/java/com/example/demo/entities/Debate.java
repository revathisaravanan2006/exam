package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    
}