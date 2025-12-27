package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
public class CapacityAlertController {
    
    @Autowired
    private CapacityAnalysisService capacityAnalysisService;
    
    @Autowired
    private CapacityAlertRepository alertRepository;

    @PostMapping("/analyze")
    public ResponseEntity<CapacityAnalysisResultDto> analyzeCapacity(
            @RequestParam String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(capacityAnalysisService.analyzeTeamCapacity(teamName, start, end));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<CapacityAlert>> getAlertsByTeam(
            @PathVariable String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(alertRepository.findByTeamNameAndDateBetween(teamName, start, end));
    }
}