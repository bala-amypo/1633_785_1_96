package com.example.demo.config;

import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import com.example.demo.service.impl.EmployeeProfileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeProfileConfig {

    @Bean
    public EmployeeProfileService employeeProfileService(
            EmployeeProfileRepository employeeProfileRepository
    ) {
        return new EmployeeProfileServiceImpl(employeeProfileRepository);
    }
}
