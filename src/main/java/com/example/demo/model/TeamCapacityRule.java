package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TeamCapacityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxLeavesPerDay;
}
