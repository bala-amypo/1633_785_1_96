package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository capacityRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository capacityRepo,
                                       EmployeeProfileRepository employeeRepo,
                                       LeaveRequestRepository leaveRepo,
                                       CapacityAlertRepository alertRepo) {
        this.capacityRepo = capacityRepo;
        this.employeeRepo = employeeRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must not be after end date");
        }

        TeamCapacityConfig config = capacityRepo.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found for team: " + teamName));

        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount: " + config.getTotalHeadcount());
        }

        List<LocalDate> dates = DateRangeUtil.daysBetween(start, end);
        Map<LocalDate, Integer> capacityMap = new HashMap<>();
        boolean risky = false;

        for (LocalDate date : dates) {
            List<LeaveRequest> overlapping = leaveRepo.findApprovedOverlappingForTeam(teamName, date, date);
            int onLeave = overlapping.size();
            int available = config.getTotalHeadcount() - onLeave;
            int percent = (int) ((available * 100.0) / config.getTotalHeadcount());

            capacityMap.put(date, percent);

            if (percent < config.getMinCapacityPercent()) {
                risky = true;
                String severity = percent < config.getMinCapacityPercent() - 20 ? "HIGH" :
                                  percent < config.getMinCapacityPercent() - 10 ? "MEDIUM" : "LOW";
                CapacityAlert alert = new CapacityAlert(teamName, date, severity,
                        "Capacity dropped to " + percent + "% on " + date);
                alertRepo.save(alert);
            }
        }

        CapacityAnalysisResultDto result = new CapacityAnalysisResultDto();
        result.setRisky(risky);
        result.setCapacityByDate(capacityMap);
        result.setMessage(risky ? "Team capacity risk detected" : "Team capacity healthy");
        return result;
    }
}