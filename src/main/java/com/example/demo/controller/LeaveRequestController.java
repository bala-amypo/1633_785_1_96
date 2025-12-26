package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveRequestDto create(@RequestBody LeaveRequestDto dto) {
        return leaveService.create(dto);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequestDto approve(@PathVariable Long id) {
        return leaveService.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequestDto reject(@PathVariable Long id) {
        return leaveService.reject(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequestDto> getByEmployee(@PathVariable Long employeeId) {
        return leaveService.getByEmployee(employeeId);
    }

    @GetMapping("/team/{team}")
    public List<LeaveRequestDto> getOverlappingForTeam(
            @PathVariable String team,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return leaveService.getOverlappingForTeam(team, start, end);
    }
}
    