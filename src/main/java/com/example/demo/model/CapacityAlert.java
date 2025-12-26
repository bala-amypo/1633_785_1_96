package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CapacityAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate date;
    private String severity;
    private String message;

    public CapacityAlert() {}

    public CapacityAlert(String teamName, LocalDate date, String severity, String message) {
        this.teamName = teamName;
        this.date = date;
        this.severity = severity;
        this.message = message;
    }

    public String getSeverity() { return severity; }
}
