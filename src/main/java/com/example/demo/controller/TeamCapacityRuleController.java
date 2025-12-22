package com.example.demo.controller;

import com.example.demo.model.TeamCapacityRule;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {

    private final TeamCapacityRuleService capacityRuleService;

    public TeamCapacityRuleController(TeamCapacityRuleService capacityRuleService) {
        this.capacityRuleService = capacityRuleService;
    }

    @PostMapping
    public TeamCapacityRule createRule(@RequestBody TeamCapacityRule rule) {
        return capacityRuleService.create(rule);
    }

    @PutMapping("/{id}")
    public TeamCapacityRule updateRule(@PathVariable Long id,
                                       @RequestBody TeamCapacityRule rule) {
        return capacityRuleService.update(id, rule);
    }

    @GetMapping("/team/{teamName}")
    public TeamCapacityRule getRuleByTeam(@PathVariable String teamName) {
        return capacityRuleService.getByTeam(teamName);
    }
}
