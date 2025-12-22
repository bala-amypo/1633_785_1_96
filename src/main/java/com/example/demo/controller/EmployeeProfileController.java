package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    // ✅ Constructor injection (MANDATORY)
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    // POST /api/employees
    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        return employeeProfileService.create(dto);
    }

    // PUT /api/employees/{id}
    @PutMapping("/{id}")
    public EmployeeProfileDto update(@PathVariable Long id,
                                     @RequestBody EmployeeProfileDto dto) {
        return employeeProfileService.update(id, dto);
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public EmployeeProfileDto getById(@PathVariable Long id) {
        return employeeProfileService.getById(id);
    }

    // GET /api/employees/team/{teamName}
    @GetMapping("/team/{teamName}")
    public List<EmployeeProfileDto> getByTeam(@PathVariable String teamName) {
        return employeeProfileService.getByTeam(teamName);
    }
}
