package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Map;

public class CapacityAnalysisResultDto {

    private boolean risky;
    private Map<LocalDate, Double> capacityByDate;

    public CapacityAnalysisResultDto() {}

    public CapacityAnalysisResultDto(boolean risky,
                                     Map<LocalDate, Double> capacityByDate) {
        this.risky = risky;
        this.capacityByDate = capacityByDate;
    }

    // getters & setters
}
