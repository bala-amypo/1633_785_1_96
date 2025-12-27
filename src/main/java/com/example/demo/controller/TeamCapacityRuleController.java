package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {
    
    @Autowired
    private TeamCapacityRuleService capacityRuleService;

    @PostMapping
    public ResponseEntity<TeamCapacityConfig> createRule(@RequestBody TeamCapacityConfig rule) {
        return ResponseEntity.ok(capacityRuleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamCapacityConfig> updateRule(@PathVariable Long id, @RequestBody TeamCapacityConfig rule) {
        return ResponseEntity.ok(capacityRuleService.updateRule(id, rule));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<TeamCapacityConfig> getRuleByTeam(@PathVariable String teamName) {
        return ResponseEntity.ok(capacityRuleService.getRuleByTeam(teamName));
    }
}