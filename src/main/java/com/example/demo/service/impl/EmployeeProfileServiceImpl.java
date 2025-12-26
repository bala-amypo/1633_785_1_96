package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile entity = new EmployeeProfile();
        BeanUtils.copyProperties(dto, entity);
        EmployeeProfile saved = repo.save(entity);
        EmployeeProfileDto result = new EmployeeProfileDto();
        BeanUtils.copyProperties(saved, result);
        return result;
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        BeanUtils.copyProperties(dto, existing, "id", "employeeId", "email", "active");
        EmployeeProfile updated = repo.save(existing);
        EmployeeProfileDto result = new EmployeeProfileDto();
        BeanUtils.copyProperties(updated, result);
        return result;
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile emp = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        emp.setActive(false);
        repo.save(emp);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile emp = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        EmployeeProfileDto dto = new EmployeeProfileDto();
        BeanUtils.copyProperties(emp, dto);
        return dto;
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return repo.findByTeamNameAndActiveTrue(teamName).stream()
                .map(e -> {
                    EmployeeProfileDto d = new EmployeeProfileDto();
                    BeanUtils.copyProperties(e, d);
                    return d;
                }).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return repo.findAll().stream()
                .map(e -> {
                    EmployeeProfileDto d = new EmployeeProfileDto();
                    BeanUtils.copyProperties(e, d);
                    return d;
                }).collect(Collectors.toList());
    }
}