package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository configRepo, com.example.demo.repository.EmployeeProfileRepository employeeRepo, LeaveRequestRepository leaveRepo, CapacityAlertRepository alertRepo) {
        this.configRepo = configRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) throw new BadRequestException("Start date must be before end date");
        TeamCapacityConfig config = configRepo.findByTeamName(teamName).orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));
        if (config.getTotalHeadcount() == null || config.getTotalHeadcount() <= 0) throw new BadRequestException("Invalid total headcount");

        List<com.example.demo.model.LeaveRequest> overlapping = leaveRepo.findApprovedOverlappingForTeam(teamName, start, end);
        int leavesCount = overlapping.size();
        int head = config.getTotalHeadcount();

        double percent = ((double)(head - leavesCount) / (double) head) * 100.0;

        boolean risky = percent < config.getMinCapacityPercent();

        Map<String, Double> byDate = new HashMap<>();
        for (LocalDate d : DateRangeUtil.daysBetween(start, end)) {
            byDate.put(d.toString(), percent);
            if (risky) {
                CapacityAlert alert = new CapacityAlert(teamName, d, "HIGH", "Capacity below threshold");
                alertRepo.save(alert);
            }
        }

        CapacityAnalysisResultDto res = new CapacityAnalysisResultDto();
        res.setRisky(risky);
        res.setCapacityByDate(byDate);
        return res;
    }
}
