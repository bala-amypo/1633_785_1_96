package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Override
    public EmployeeProfileDto createEmployeeProfile(EmployeeProfileDto dto) {
        EmployeeProfile entity = convertToEntity(dto);
        EmployeeProfile saved = employeeProfileRepository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        return employeeProfileRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    private EmployeeProfileDto convertToDto(EmployeeProfile entity) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        // Add other mappings
        return dto;
    }

    private EmployeeProfile convertToEntity(EmployeeProfileDto dto) {
        EmployeeProfile entity = new EmployeeProfile();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        // Add other mappings
        return entity;
    }
}
