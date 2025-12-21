package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final TeamCapacityConfigRepository teamCapacityConfigRepository;
    private final CapacityAlertRepository capacityAlertRepository;

    // ✅ Constructor injection (MANDATORY)
    public CapacityAnalysisServiceImpl(EmployeeProfileRepository employeeProfileRepository,
                                       LeaveRequestRepository leaveRequestRepository,
                                       TeamCapacityConfigRepository teamCapacityConfigRepository,
                                       CapacityAlertRepository capacityAlertRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.teamCapacityConfigRepository = teamCapacityConfigRepository;
        this.capacityAlertRepository = capacityAlertRepository;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(
            String teamName,
            LocalDate start,
            LocalDate end) {

        TeamCapacityConfig config = teamCapacityConfigRepository
                .findByTeamName(teamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capacity rule not found"));

        List<EmployeeProfile> employees =
                employeeProfileRepository.findByTeamName(teamName);

        Map<LocalDate, Integer> availableCapacity = new HashMap<>();
        boolean alertGenerated = false;

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {

            int totalCapacity = employees.stream()
                    .mapToInt(EmployeeProfile::getDailyCapacity)
                    .sum();

            int onLeaveCount = 0;

            for (EmployeeProfile emp : employees) {
                List<LeaveRequest> leaves =
                        leaveRequestRepository.findByEmployeeId(emp.getId());

                boolean onLeave = leaves.stream().anyMatch(l ->
                        !l.getStartDate().isAfter(date) &&
                        !l.getEndDate().isBefore(date) &&
                        "APPROVED".equals(l.getStatus())
                );

                if (onLeave) {
                    onLeaveCount += emp.getDailyCapacity();
                }
            }

            int remainingCapacity = totalCapacity - onLeaveCount;
            availableCapacity.put(date, remainingCapacity);

            if (remainingCapacity < config.getMaxCapacity()) {
                alertGenerated = true;

                CapacityAlert alert = new CapacityAlert();
                alert.setTeamName(teamName);
                alert.setDate(date);
                alert.setAvailableCapacity(remainingCapacity);
                alert.setMessage("Capacity below threshold");

                capacityAlertRepository.save(alert);
            }
        }

        CapacityAnalysisResultDto result = new CapacityAnalysisResultDto();
        result.setTeamName(teamName);
        result.setStartDate(start);
        result.setEndDate(end);
        result.setAvailableCapacity(availableCapacity);
        result.setAlertGenerated(alertGenerated);

        return result;
    }
}
