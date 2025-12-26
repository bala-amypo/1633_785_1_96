package com.example.demo.config;

import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.service.impl.CapacityAnalysisServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CapacityAnalysisConfig {

    @Bean
    public CapacityAnalysisService capacityAnalysisService(
            TeamCapacityConfigRepository teamCapacityConfigRepository,
            EmployeeProfileRepository employeeProfileRepository,
            LeaveRequestRepository leaveRequestRepository,
            CapacityAlertRepository capacityAlertRepository) {

        return new CapacityAnalysisServiceImpl(
                teamCapacityConfigRepository,
                employeeProfileRepository,
                leaveRequestRepository,
                capacityAlertRepository
        );
    }
}
