package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "employee_profiles",
       uniqueConstraints = @UniqueConstraint(columnNames = "employeeId"))
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String role;

    private boolean active = true;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "employee_colleagues",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "colleague_id")
    )
    private Set<EmployeeProfile> colleagues = new HashSet<>();

    public void addColleague(EmployeeProfile colleague) {
        colleagues.add(colleague);
        colleague.getColleagues().add(this);
    }

    public void removeColleague(EmployeeProfile colleague) {
        colleagues.remove(colleague);
        colleague.getColleagues().remove(this);
    }
}