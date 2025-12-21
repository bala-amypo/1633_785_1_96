package com.example.demo.controller;

import com.example.demo.repository.CapacityAlertRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAlertRepository repo;

    public CapacityAlertController(CapacityAlertRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/analyze")
    public void analyze() {}

    @GetMapping("/team/{teamName}")
    public Object getAlerts(@PathVariable String teamName) {
        return repo.findByTeamNameAndDateBetween(teamName, null, null);
    }
}
