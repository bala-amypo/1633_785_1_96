package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

 
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

  
    @PostMapping
    public EmployeeProfileDto create(@RequestBody EmployeeProfileDto dto) {
        return employeeProfileService.create(dto);
    }

  
    @PutMapping("/{id}")
    public EmployeeProfileDto update(@PathVariable Long id,
                                     @RequestBody EmployeeProfileDto dto) {
        return employeeProfileService.update(id, dto);
    }

 
    @GetMapping("/{id}")
    public EmployeeProfileDto getById(@PathVariable Long id) {
        return employeeProfileService.getById(id);
    }

   
    @GetMapping("/team/{teamName}")
    public List<EmployeeProfileDto> getByTeam(@PathVariable String teamName) {
        return employeeProfileService.getByTeam(teamName);
    }
}
