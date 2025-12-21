package com.example.demo.service;

import com.example.demo.model.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig createRule(TeamCapacityConfig rule);

    TeamCapacityConfig getRuleByTeam(String teamName);
}
