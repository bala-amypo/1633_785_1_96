package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private int totalHeadcount;
    private int minCapacityPercent;

    // getters & setters
}
