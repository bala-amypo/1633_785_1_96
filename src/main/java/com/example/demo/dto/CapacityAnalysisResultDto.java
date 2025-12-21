package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Map;

public class CapacityAnalysisResultDto {

    private String teamName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<LocalDate, Integer> availableCapacity;
    private boolean alertGenerated;

    public CapacityAnalysisResultDto() {
    }

    public CapacityAnalysisResultDto(String teamName,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     Map<LocalDate, Integer> availableCapacity,
                                     boolean alertGenerated) {
        this.teamName = teamName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableCapacity = availableCapacity;
        this.alertGenerated = alertGenerated;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Map<LocalDate, Integer> getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Map<LocalDate, Integer> availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public boolean isAlertGenerated() {
        return alertGenerated;
    }

    public void setAlertGenerated(boolean alertGenerated) {
        this.alertGenerated = alertGenerated;
    }
}
