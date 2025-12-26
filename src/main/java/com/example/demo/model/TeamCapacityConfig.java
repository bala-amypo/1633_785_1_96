package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "team_capacity_configs",
       uniqueConstraints = @UniqueConstraint(columnNames = "teamName"))
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String teamName;

    @Column(nullable = false)
    private int totalHeadcount;

    @Column(nullable = false)
    private int minCapacityPercent; // e.g., 60 for 60%
}