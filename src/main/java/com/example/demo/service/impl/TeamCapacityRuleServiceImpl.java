package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository repository;

    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamCapacityConfig createRule(TeamCapacityConfig rule) {
        return repository.save(rule);
    }

    @Override
    public TeamCapacityConfig getRuleByTeam(String teamName) {

        return repository.findByTeamName(teamName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Capacity rule not found"));
    }
}
