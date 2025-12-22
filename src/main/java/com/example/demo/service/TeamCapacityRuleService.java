package com.example.demo.service;

import com.example.demo.model.TeamCapacityRule;

public interface TeamCapacityRuleService {

    TeamCapacityRule create(TeamCapacityRule rule);

    TeamCapacityRule update(Long id, TeamCapacityRule rule);

    TeamCapacityRule getByTeam(String teamName);
}
