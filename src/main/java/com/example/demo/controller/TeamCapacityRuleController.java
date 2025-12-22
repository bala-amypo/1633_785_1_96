package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService teamCapacityRuleService;

   
    public TeamCapacityRuleController(TeamCapacityRuleService teamCapacityRuleService) {
        this.teamCapacityRuleService = teamCapacityRuleService;
    }

 
    @PostMapping
    public TeamCapacityConfig create(@RequestBody TeamCapacityConfig rule) {
        return teamCapacityRuleService.createRule(rule);
    }

  
    @GetMapping("/team/{teamName}")
    public TeamCapacityConfig getByTeam(@PathVariable String teamName) {
        return teamCapacityRuleService.getRuleByTeam(teamName);
    }
}
