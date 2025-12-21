package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
@Tag(name = "Team Capacity Rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService service;

    public TeamCapacityRuleController(TeamCapacityRuleService service) {
        this.service = service;
    }

    @PostMapping
    public TeamCapacityConfig create(@RequestBody TeamCapacityConfig rule) {
        return service.createRule(rule);
    }

    @GetMapping("/team/{teamName}")
    public TeamCapacityConfig get(@PathVariable String teamName) {
        return service.getRuleByTeam(teamName);
    }
}
