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

    // ✅ CONSTRUCTOR NAME MUST MATCH CLASS NAME
    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());

        EmployeeProfile saved = repo.save(e);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (dto.getFullName() != null) e.setFullName(dto.getFullName());
        if (dto.getTeamName() != null) e.setTeamName(dto.getTeamName());
        if (dto.getRole() != null) e.setRole(dto.getRole());

        repo.save(e);

        EmployeeProfileDto out = new EmployeeProfileDto();
        out.setId(e.getId());
        out.setEmployeeId(e.getEmployeeId());
        out.setFullName(e.getFullName());
        out.setEmail(e.getEmail());
        out.setTeamName(e.getTeamName());
        out.setRole(e.getRole());
        return out;
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        e.setActive(false);
        repo.save(e);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(e.getId());
        dto.setEmployeeId(e.getEmployeeId());
        dto.setFullName(e.getFullName());
        dto.setEmail(e.getEmail());
        dto.setTeamName(e.getTeamName());
        dto.setRole(e.getRole());
        return dto;
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return repo.findByTeamNameAndActiveTrue(teamName)
                .stream()
                .map(e -> {
                    EmployeeProfileDto d = new EmployeeProfileDto();
                    d.setId(e.getId());
                    d.setEmployeeId(e.getEmployeeId());
                    d.setFullName(e.getFullName());
                    d.setEmail(e.getEmail());
                    d.setTeamName(e.getTeamName());
                    d.setRole(e.getRole());
                    return d;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return repo.findAll()
                .stream()
                .map(e -> {
                    EmployeeProfileDto d = new EmployeeProfileDto();
                    d.setId(e.getId());
                    d.setEmployeeId(e.getEmployeeId());
                    d.setFullName(e.getFullName());
                    d.setEmail(e.getEmail());
                    d.setTeamName(e.getTeamName());
                    d.setRole(e.getRole());
                    return d;
                })
                .collect(Collectors.toList());
    }
}
