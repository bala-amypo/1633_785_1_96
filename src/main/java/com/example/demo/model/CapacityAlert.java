package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CapacityAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate date;
    private String severity;
    private String message;

    public CapacityAlert() {}

    public CapacityAlert(String team, LocalDate date, String severity, String msg) {
        this.teamName = team;
        this.date = date;
        this.severity = severity;
        this.message = msg;
    }
}
