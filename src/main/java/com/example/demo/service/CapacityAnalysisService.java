package com.example.demo.service;

import com.example.demo.model.CapacityAlert;

import java.time.LocalDate;
import java.util.List;

public interface CapacityAnalysisService {

    List<CapacityAlert> analyze(String teamName, LocalDate startDate, LocalDate endDate);

    List<CapacityAlert> getAlertsByTeam(String teamName);
}
