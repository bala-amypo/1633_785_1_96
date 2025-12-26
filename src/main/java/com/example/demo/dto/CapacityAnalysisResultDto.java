package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Map;

public class CapacityAnalysisResultDto {
    private boolean risky;
    private Map<LocalDate, Integer> capacityByDate;

    public CapacityAnalysisResultDto(boolean risky, Map<LocalDate, Integer> map) {
        this.risky = risky;
        this.capacityByDate = map;
    }

    public boolean isRisky() { return risky; }
    public Map<LocalDate, Integer> getCapacityByDate() { return capacityByDate; }
}
