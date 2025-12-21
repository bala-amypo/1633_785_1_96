package com.example.demo.dto;

public class EmployeeProfileDto {

    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;

    public EmployeeProfileDto() {}

    public EmployeeProfileDto(Long id, String employeeId, String fullName,
                              String email, String teamName, String role) {
        this.id = id;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.teamName = teamName;
        this.role = role;
    }

    // getters & setters
}
