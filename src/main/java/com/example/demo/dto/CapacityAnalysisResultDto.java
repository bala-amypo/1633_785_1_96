package com.example.demo.dto;

import java.util.Map;

public class CapacityAnalysisResultDto {
    private boolean risky;
    private Map<String, Double> capacityByDate;

    public boolean isRisky() {
        return risky;
    }

    public void setRisky(boolean risky) {
        this.risky = risky;
    }

    public Map<String, Double> getCapacityByDate() {
        return capacityByDate;
    }

    public void setCapacityByDate(Map<String, Double> capacityByDate) {
        this.capacityByDate = capacityByDate;
    }
}
