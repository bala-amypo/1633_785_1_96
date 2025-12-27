// File: src/main/java/com/example/demo/model/EmployeeProfile.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_profile")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String employeeId;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String teamName;
    
    @Column(nullable = false)
    private String role;
    
    private boolean active = true;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<LeaveRequest> leaveRequests = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "employee_colleagues",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    private Set<EmployeeProfile> colleagues = new HashSet<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public Set<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    public void setLeaveRequests(Set<LeaveRequest> leaveRequests) { this.leaveRequests = leaveRequests; }
    
    public Set<EmployeeProfile> getColleagues() { return colleagues; }
    public void setColleagues(Set<EmployeeProfile> colleagues) { this.colleagues = colleagues; }
}
