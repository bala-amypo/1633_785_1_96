package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {
    
    @Autowired
    private LeaveRequestService leaveService;

    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeave(@RequestBody LeaveRequestDto dto) {
        return ResponseEntity.ok(leaveService.create(dto));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequestDto> approveLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.approve(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequestDto> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.reject(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getByEmployee(employeeId));
    }

    @GetMapping("/team-overlap")
    public ResponseEntity<List<LeaveRequestDto>> getOverlappingLeaves(
            @RequestParam String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(leaveService.getOverlappingForTeam(teamName, start, end));
    }
}