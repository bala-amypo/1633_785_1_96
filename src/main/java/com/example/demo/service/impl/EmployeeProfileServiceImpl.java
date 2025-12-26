package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());
        repo.save(e);
        dto.setId(e.getId());
        return dto;
    }

    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (dto.getFullName() != null) e.setFullName(dto.getFullName());
        if (dto.getTeamName() != null) e.setTeamName(dto.getTeamName());
        if (dto.getRole() != null) e.setRole(dto.getRole());
        repo.save(e);
        dto.setId(id);
        return dto;
    }

    public void deactivate(Long id) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        e.setActive(false);
        repo.save(e);
    }

    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setEmployeeId(e.getEmployeeId());
        return dto;
    }

    public List<EmployeeProfileDto> getAll() {
        return repo.findAll().stream().map(e -> {
            EmployeeProfileDto d = new EmployeeProfileDto();
            d.setEmployeeId(e.getEmployeeId());
            return d;
        }).collect(Collectors.toList());
    }

    public List<EmployeeProfileDto> getByTeam(String team) {
        return repo.findByTeamNameAndActiveTrue(team)
                .stream().map(e -> {
                    EmployeeProfileDto d = new EmployeeProfileDto();
                    d.setEmployeeId(e.getEmployeeId());
                    return d;
                }).collect(Collectors.toList());
    }
}
