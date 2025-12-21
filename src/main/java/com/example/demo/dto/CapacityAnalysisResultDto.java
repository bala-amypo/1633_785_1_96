package com.example.demo.dto;

import java.util.Map;

public class CapacityAnalysisResultDto {

    private Map<String, Integer> capacityByDate;
    private boolean risky;

    public Map<String, Integer> getCapacityByDate() {
        return capacityByDate;
    }

    public boolean isRisky() {
        return risky;
    }
}
