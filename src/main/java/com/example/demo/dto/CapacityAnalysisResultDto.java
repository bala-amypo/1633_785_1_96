// File: src/main/java/com/example/demo/dto/CapacityAnalysisResultDto.java
package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Map;

public class CapacityAnalysisResultDto {
    private boolean risky;
    private Map<LocalDate, Integer> capacityByDate;
    
    public boolean isRisky() { return risky; }
    public void setRisky(boolean risky) { this.risky = risky; }
    
    public Map<LocalDate, Integer> getCapacityByDate() { return capacityByDate; }
    public void setCapacityByDate(Map<LocalDate, Integer> capacityByDate) { 
        this.capacityByDate = capacityByDate; 
    }
}
