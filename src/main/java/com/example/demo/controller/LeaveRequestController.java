package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

   
    @PostMapping
    public LeaveRequestDto create(@RequestBody LeaveRequestDto dto) {
        return leaveRequestService.create(dto);
    }

   
    @PutMapping("/{id}/approve")
    public LeaveRequestDto approve(@PathVariable Long id) {
        return leaveRequestService.approve(id);
    }

    
    @PutMapping("/{id}/reject")
    public LeaveRequestDto reject(@PathVariable Long id) {
        return leaveRequestService.reject(id);
    }

   
    @GetMapping("/employee/{id}")
    public List<LeaveRequestDto> getByEmployee(@PathVariable Long id) {
        return leaveRequestService.getByEmployee(id);
    }
}
