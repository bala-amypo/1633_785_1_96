package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
public class CapacityAnalysisResultDto {
    private boolean risky;
    private Map<LocalDate, Integer> capacityByDate;
}
