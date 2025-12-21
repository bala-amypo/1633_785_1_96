package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_config")
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private Integer maxCapacity;

    public TeamCapacityConfig() {
    }

    public TeamCapacityConfig(Long id, String teamName, Integer maxCapacity) {
        this.id = id;
        this.teamName = teamName;
        this.maxCapacity = maxCapacity;
    }

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

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
