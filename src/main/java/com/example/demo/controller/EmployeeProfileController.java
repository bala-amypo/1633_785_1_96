package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {
    
    @Autowired
    private EmployeeProfileService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        return ResponseEntity.ok(employeeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        return ResponseEntity.ok(employeeService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<EmployeeProfileDto>> getEmployeesByTeam(@PathVariable String teamName) {
        return ResponseEntity.ok(employeeService.getByTeam(teamName));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }
}