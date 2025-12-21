package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityRule;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class CapacityAnalysisServiceImpl {

    private final TeamCapacityConfigRepository configRepo;
    private final EmployeeProfileRepository employeeRepo;

    public CapacityAnalysisServiceImpl(
            TeamCapacityConfigRepository configRepo,
            EmployeeProfileRepository employeeRepo) {
        this.configRepo = configRepo;
        this.employeeRepo = employeeRepo;
    }

    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName, LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new BadRequestException("Start date invalid");
        }

        TeamCapacityRule rule = configRepo.findByTeamName(teamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capacity config not found"));

        if (rule.getTotalHeadcount() < 1) {
            throw new BadRequestException("Invalid total headcount");
        }

        CapacityAnalysisResultDto dto = new CapacityAnalysisResultDto();
        dto.capacityByDate = new HashMap<>();
        dto.risky = false;

        return dto;
    }
}
