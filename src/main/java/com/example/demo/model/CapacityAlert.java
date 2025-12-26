package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "capacity_alerts")
public class CapacityAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String severity; // LOW, MEDIUM, HIGH

    private String message;

    public CapacityAlert() {}

    public CapacityAlert(String teamName, LocalDate date, String severity, String message) {
        this.teamName = teamName;
        this.date = date;
        this.severity = severity;
        this.message = message;
    }
}