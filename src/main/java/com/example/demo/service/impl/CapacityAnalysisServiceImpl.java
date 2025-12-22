package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityRule;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final LeaveRequestRepository leaveRepository;
    private final CapacityAlertRepository alertRepository;
    private final TeamCapacityRuleService ruleService;

    public CapacityAnalysisServiceImpl(LeaveRequestRepository leaveRepository,
                                       CapacityAlertRepository alertRepository,
                                       TeamCapacityRuleService ruleService) {
        this.leaveRepository = leaveRepository;
        this.alertRepository = alertRepository;
        this.ruleService = ruleService;
    }

    @Override
    public List<CapacityAlert> analyze(String teamName, LocalDate startDate, LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Invalid Date Range: Start date after end date");
        }

        TeamCapacityRule rule = ruleService.getByTeam(teamName);

        List<LeaveRequest> approvedLeaves =
                leaveRepository.findByEmployee_TeamNameAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        teamName, "APPROVED", endDate, startDate
                );

        int unavailable = approvedLeaves.size();
        double percentUnavailable =
                ((double) unavailable / rule.getTotalHeadcount()) * 100;

        List<CapacityAlert> alerts = new ArrayList<>();

        if (percentUnavailable >= rule.getMinCapacityPercent()) {
            CapacityAlert alert = new CapacityAlert();
            alert.setTeamName(teamName);
            alert.setDate(startDate);
            alert.setSeverity("HIGH");
            alert.setMessage("Team capacity exceeded threshold");

            alerts.add(alertRepository.save(alert));
        }

        return alerts;
    }

    @Override
    public List<CapacityAlert> getAlertsByTeam(String teamName) {
        return alertRepository.findByTeamNameAndDateBetween(
                teamName, LocalDate.MIN, LocalDate.MAX
        );
    }
}
