// File: src/main/java/com/example/demo/model/CapacityAlert.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "capacity_alert")
public class CapacityAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String teamName;
    
    @Column(nullable = false)
    private LocalDate alertDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertSeverity severity;
    
    private String message;

    public CapacityAlert() {}
    
    public CapacityAlert(String teamName, LocalDate alertDate, AlertSeverity severity, String message) {
        this.teamName = teamName;
        this.alertDate = alertDate;
        this.severity = severity;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    
    public LocalDate getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDate alertDate) { this.alertDate = alertDate; }
    
    public AlertSeverity getSeverity() { return severity; }
    public void setSeverity(AlertSeverity severity) { this.severity = severity; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

// PUBLIC ENUM
public enum AlertSeverity {
    LOW, MEDIUM, HIGH
}
