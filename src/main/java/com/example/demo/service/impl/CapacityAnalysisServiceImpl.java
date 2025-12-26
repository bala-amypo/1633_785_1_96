package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;

import java.time.LocalDate;
import java.util.*;

public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(
            TeamCapacityConfigRepository configRepo,
            EmployeeProfileRepository employeeRepo,
            LeaveRequestRepository leaveRepo,
            CapacityAlertRepository alertRepo) {
        this.configRepo = configRepo;
        this.employeeRepo = employeeRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String team, LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must be before end date");
        }

        TeamCapacityConfig config = configRepo.findByTeamName(team)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }

        Map<LocalDate, Integer> capacityMap = new HashMap<>();
        boolean risky = false;

        for (LocalDate d : DateRangeUtil.daysBetween(start, end)) {
            int leaves = leaveRepo
                    .findApprovedOverlappingForTeam(team, d, d).size();
            int available = config.getTotalHeadcount() - leaves;
            int percent = (available * 100) / config.getTotalHeadcount();
            capacityMap.put(d, percent);
            if (percent < config.getMinCapacityPercent()) {
                risky = true;
                alertRepo.save(new CapacityAlert(team, d, "HIGH", "Low capacity"));
            }
        }

        CapacityAnalysisResultDto dto = new CapacityAnalysisResultDto();
        dto.setRisky(risky);
        dto.setCapacityByDate(capacityMap);
        return dto;
    }
}
