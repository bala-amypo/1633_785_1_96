package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/capacity")
public class CapacityAlertController {

    private final CapacityAnalysisService capacityService;

    public CapacityAlertController(CapacityAnalysisService capacityService) {
        this.capacityService = capacityService;
    }

    @GetMapping("/analyze/{team}")
    public CapacityAnalysisResultDto analyze(
            @PathVariable String team,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        return capacityService.analyzeTeamCapacity(team, start, end);
    }
}
