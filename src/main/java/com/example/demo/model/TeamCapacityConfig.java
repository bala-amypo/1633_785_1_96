package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TeamCapacityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private Integer totalHeadcount;
    private Integer minCapacityPercent;

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

    public Integer getTotalHeadcount() {
        return totalHeadcount;
    }

    public void setTotalHeadcount(Integer totalHeadcount) {
        this.totalHeadcount = totalHeadcount;
    }

    public Integer getMinCapacityPercent() {
        return minCapacityPercent;
    }

    public void setMinCapacityPercent(Integer minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
}
