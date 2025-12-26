package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        return employeeService.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeProfileDto update(@PathVariable Long id,
                                     @RequestBody EmployeeProfileDto dto) {
        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        employeeService.deactivate(id);
    }

    @GetMapping("/{id}")
    public EmployeeProfileDto getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeProfileDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/team/{teamName}")
    public List<EmployeeProfileDto> getByTeam(@PathVariable String teamName) {
        return employeeService.getByTeam(teamName);
    }
}
