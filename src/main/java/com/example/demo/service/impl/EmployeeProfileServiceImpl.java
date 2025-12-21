package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
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
    public EmployeeProfileDto create(EmployeeProfileDto dto) {

        EmployeeProfile emp = new EmployeeProfile();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setTeamName(dto.getTeamName());
        emp.setDailyCapacity(dto.getDailyCapacity());

        return toDto(employeeProfileRepository.save(emp));
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {

        EmployeeProfile emp = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setTeamName(dto.getTeamName());
        emp.setDailyCapacity(dto.getDailyCapacity());

        return toDto(employeeProfileRepository.save(emp));
    }

    @Override
    public EmployeeProfileDto getById(Long id) {

        EmployeeProfile emp = employeeProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return toDto(emp);
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String teamName) {

        return employeeProfileRepository.findByTeamName(teamName)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private EmployeeProfileDto toDto(EmployeeProfile emp) {
        return new EmployeeProfileDto(
                emp.getId(),
                emp.getName(),
                emp.getEmail(),
                emp.getTeamName(),
                emp.getDailyCapacity()
        );
    }
}
