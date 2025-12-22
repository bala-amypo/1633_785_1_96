package com.example.demo.controller;

import com.example.demo.model.CapacityAlert;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService analysisService;

    public CapacityAlertController(CapacityAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analyze")
    public List<CapacityAlert> analyzeCapacity(@RequestParam String teamName,
                                                @RequestParam LocalDate startDate,
                                                @RequestParam LocalDate endDate) {
        return analysisService.analyze(teamName, startDate, endDate);
    }

    @GetMapping("/team/{teamName}")
    public List<CapacityAlert> getAlertsByTeam(@PathVariable String teamName) {
        return analysisService.getAlertsByTeam(teamName);
    }
}
