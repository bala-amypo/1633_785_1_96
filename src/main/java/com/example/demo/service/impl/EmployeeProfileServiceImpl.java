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

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());
        e.setActive(dto.getActive() == null ? true : dto.getActive());
        EmployeeProfile saved = repo.save(e);
        EmployeeProfileDto res = new EmployeeProfileDto();
        res.setId(saved.getId());
        res.setEmployeeId(saved.getEmployeeId());
        res.setFullName(saved.getFullName());
        res.setEmail(saved.getEmail());
        res.setTeamName(saved.getTeamName());
        res.setRole(saved.getRole());
        res.setActive(saved.getActive());
        return res;
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (dto.getFullName() != null) existing.setFullName(dto.getFullName());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getTeamName() != null) existing.setTeamName(dto.getTeamName());
        if (dto.getRole() != null) existing.setRole(dto.getRole());
        EmployeeProfile saved = repo.save(existing);
        EmployeeProfileDto res = new EmployeeProfileDto();
        res.setId(saved.getId());
        res.setEmployeeId(saved.getEmployeeId());
        res.setFullName(saved.getFullName());
        res.setEmail(saved.getEmail());
        res.setTeamName(saved.getTeamName());
        res.setRole(saved.getRole());
        res.setActive(saved.getActive());
        return res;
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existing.setActive(false);
        repo.save(existing);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        EmployeeProfileDto res = new EmployeeProfileDto();
        res.setId(e.getId());
        res.setEmployeeId(e.getEmployeeId());
        res.setFullName(e.getFullName());
        res.setEmail(e.getEmail());
        res.setTeamName(e.getTeamName());
        res.setRole(e.getRole());
        res.setActive(e.getActive());
        return res;
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return repo.findByTeamNameAndActiveTrue(teamName).stream().map(e -> {
            EmployeeProfileDto d = new EmployeeProfileDto();
            d.setId(e.getId());
            d.setEmployeeId(e.getEmployeeId());
            d.setFullName(e.getFullName());
            d.setEmail(e.getEmail());
            d.setTeamName(e.getTeamName());
            d.setRole(e.getRole());
            d.setActive(e.getActive());
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return repo.findAll().stream().map(e -> {
            EmployeeProfileDto d = new EmployeeProfileDto();
            d.setId(e.getId());
            d.setEmployeeId(e.getEmployeeId());
            d.setFullName(e.getFullName());
            d.setEmail(e.getEmail());
            d.setTeamName(e.getTeamName());
            d.setRole(e.getRole());
            d.setActive(e.getActive());
            return d;
        }).collect(Collectors.toList());
    }
}
