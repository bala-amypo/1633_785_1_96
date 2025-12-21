package com.example.demo.service.impl;

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
import com.example.demo.service.CapacityAnalysisService;
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

        LocalDate currentDate = start;

        while (!currentDate.isAfter(end)) {

            int totalCapacity = 0;
            for (EmployeeProfile emp : employees) {
                totalCapacity += emp.getDailyCapacity();
            }

            int onLeaveCapacity = 0;

            for (EmployeeProfile emp : employees) {
                List<LeaveRequest> leaves =
                        leaveRequestRepository.findByEmployeeId(emp.getId());

                for (LeaveRequest leave : leaves) {
                    if ("APPROVED".equals(leave.getStatus())
                            && !leave.getStartDate().isAfter(currentDate)
                            && !leave.getEndDate().isBefore(currentDate)) {

                        onLeaveCapacity += emp.getDailyCapacity();
                        break;
                    }
                }
            }

            int remainingCapacity = totalCapacity - onLeaveCapacity;
            availableCapacity.put(currentDate, remainingCapacity);

            if (remainingCapacity < config.getMaxCapacity()) {
                alertGenerated = true;

                CapacityAlert alert = new CapacityAlert();
                alert.setTeamName(teamName);
                alert.setDate(currentDate);
                alert.setAvailableCapacity(remainingCapacity);
                alert.setMessage("Capacity below threshold");

                capacityAlertRepository.save(alert);
            }

            currentDate = currentDate.plusDays(1);
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
