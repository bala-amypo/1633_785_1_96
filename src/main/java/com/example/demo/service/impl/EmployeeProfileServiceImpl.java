// File: src/main/java/com/example/demo/service/impl/EmployeeProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeRepo;

    @Autowired
    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile entity = new EmployeeProfile();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setTeamName(dto.getTeamName());
        entity.setRole(dto.getRole());
        EmployeeProfile saved = employeeRepo.save(entity);
        return convertToDto(saved);
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existing.setFullName(dto.getFullName());
        existing.setTeamName(dto.getTeamName());
        existing.setRole(dto.getRole());
        EmployeeProfile updated = employeeRepo.save(existing);
        return convertToDto(updated);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile entity = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return convertToDto(entity);
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile existing = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existing.setActive(false);
        employeeRepo.save(existing);
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {
        return employeeRepo.findByTeamNameAndActiveTrue(teamName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return employeeRepo.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EmployeeProfileDto convertToDto(EmployeeProfile entity) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setTeamName(entity.getTeamName());
        dto.setRole(entity.getRole());
        return dto;
    }
}
