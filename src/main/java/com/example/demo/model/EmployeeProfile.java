package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // REQUIRED for JPQL query
    private String teamName;

    public EmployeeProfile() {}

    public EmployeeProfile(Long id, String name, String email, String teamName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.teamName = teamName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
}
