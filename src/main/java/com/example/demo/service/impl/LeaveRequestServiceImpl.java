package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.LeaveRequestService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repo;
    private final EmployeeProfileRepository empRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository r, EmployeeProfileRepository e) {
        this.repo = r;
        this.empRepo = e;
    }

    public LeaveRequestDto create(LeaveRequestDto dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate()))
            throw new BadRequestException("Invalid dates");

        EmployeeProfile emp = empRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveRequest l = new LeaveRequest();
        l.setEmployee(emp);
        l.setStartDate(dto.getStartDate());
        l.setEndDate(dto.getEndDate());
        l.setType(dto.getType());
        l.setStatus("PENDING");

        repo.save(l);
        dto.setId(l.getId());
        dto.setStatus("PENDING");
        return dto;
    }

    public LeaveRequestDto approve(Long id) {
        LeaveRequest l = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        l.setStatus("APPROVED");
        repo.save(l);
        LeaveRequestDto d = new LeaveRequestDto();
        d.setStatus("APPROVED");
        return d;
    }

    public LeaveRequestDto reject(Long id) {
        LeaveRequest l = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        l.setStatus("REJECTED");
        repo.save(l);
        LeaveRequestDto d = new LeaveRequestDto();
        d.setStatus("REJECTED");
        return d;
    }

    public List<LeaveRequestDto> getByEmployee(Long id) {
        EmployeeProfile emp = empRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return repo.findByEmployee(emp).stream().map(l -> new LeaveRequestDto()).collect(Collectors.toList());
    }

    public List<LeaveRequestDto> getOverlappingForTeam(String team, LocalDate s, LocalDate e) {
        return repo.findApprovedOverlappingForTeam(team, s, e)
                .stream().map(l -> new LeaveRequestDto()).collect(Collectors.toList());
    }
}
