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
    private int availableCapacity;

    @Column(nullable = false)
    private int requiredCapacity;

    @Column(nullable = false)
    private String status; // LOW / OK / CRITICAL

    @Column(nullable = false)
    private LocalDate alertDate;

    // ---------- Constructors ----------
    public CapacityAlert() {}

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public void setRequiredCapacity(int requiredCapacity) {
        this.requiredCapacity = requiredCapacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
    }
}
