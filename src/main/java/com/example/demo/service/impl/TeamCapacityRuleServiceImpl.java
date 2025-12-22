package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    private final TeamCapacityConfigRepository repository;

    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamCapacityRule create(TeamCapacityRule rule) {

        if (rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }

        return repository.save(rule);
    }

    @Override
    public TeamCapacityRule update(Long id, TeamCapacityRule rule) {
        TeamCapacityRule existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        rule.setId(existing.getId());
        return repository.save(rule);
    }

    @Override
    public TeamCapacityRule getByTeam(String teamName) {
        return repository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));
    }
}
