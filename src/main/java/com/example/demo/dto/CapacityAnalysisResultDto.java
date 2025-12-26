package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
            String team,
            LocalDate start,
            LocalDate end) {

        // 1️⃣ Validate date range
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date cannot be after end date");
        }

        // 2️⃣ Load capacity config
        TeamCapacityConfig config = configRepo.findByTeamName(team)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capacity config not found"));

        // 3️⃣ Validate headcount
        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }

        Map<LocalDate, Integer> capacityByDate = new HashMap<>();
        boolean risky = false;

        // 4️⃣ Calculate capacity day-by-day
        for (LocalDate date : DateRangeUtil.daysBetween(start, end)) {

            int approvedLeaves =
                    leaveRepo.findApprovedOverlappingForTeam(team, date, date).size();

            int available = config.getTotalHeadcount() - approvedLeaves;

            int capacityPercent =
                    (int) (((double) available / config.getTotalHeadcount()) * 100);

            capacityByDate.put(date, capacityPercent);

            // 5️⃣ Generate alert if below threshold
            if (capacityPercent < config.getMinCapacityPercent()) {
                risky = true;

                CapacityAlert alert = new CapacityAlert(
                        team,
                        date,
                        "HIGH",
                        "Capacity below threshold");

                alertRepo.save(alert);
            }
        }

        // 6️⃣ IMPORTANT: use constructor (NO setters)
        return new CapacityAnalysisResultDto(risky, capacityByDate);
    }
}
