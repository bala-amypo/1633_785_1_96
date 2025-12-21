package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String teamName;
    private String role;
    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
}