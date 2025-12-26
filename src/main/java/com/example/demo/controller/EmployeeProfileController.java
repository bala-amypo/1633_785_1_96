package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-profiles")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    // ✅ Constructor name MUST match class name
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @GetMapping("/{id}")
    public EmployeeProfile getEmployeeProfile(@PathVariable Long id) {
        return employeeProfileService.getEmployeeById(id);
    }
}
