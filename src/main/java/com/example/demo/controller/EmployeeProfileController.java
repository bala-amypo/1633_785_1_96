package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
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
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public EmployeeProfile updateEmployee(@PathVariable Long id,
                                          @RequestBody EmployeeProfile employee) {
        return employeeService.update(id, employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getEmployee(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/team/{teamName}")
    public List<EmployeeProfile> getEmployeesByTeam(@PathVariable String teamName) {
        return employeeService.getByTeam(teamName);
    }
}
