package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;

import java.time.LocalDate;
@service
public interface CapacityAnalysisService {
    CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end);
}
