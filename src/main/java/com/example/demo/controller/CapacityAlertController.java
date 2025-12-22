package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService capacityAnalysisService;
    private final CapacityAlertRepository capacityAlertRepository;

  
    public CapacityAlertController(CapacityAnalysisService capacityAnalysisService,
                                   CapacityAlertRepository capacityAlertRepository) {
        this.capacityAnalysisService = capacityAnalysisService;
        this.capacityAlertRepository = capacityAlertRepository;
    }

   
    @PostMapping("/analyze")
    public CapacityAnalysisResultDto analyze(
            @RequestParam String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return capacityAnalysisService.analyzeTeamCapacity(teamName, start, end);
    }

   
    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getByTeam(
            @PathVariable String teamName,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return capacityAlertRepository
                .findByTeamNameAndDateBetween(teamName, start, end);
    }
}
