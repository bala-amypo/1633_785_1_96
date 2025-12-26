package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo, EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (dto.getStartDate() == null || dto.getEndDate() == null) throw new BadRequestException("Start/End date required");
        if (dto.getStartDate().isAfter(dto.getEndDate())) throw new BadRequestException("Start date must be <= end date");
        LeaveRequest l = new LeaveRequest();
        l.setEmployee(emp);
        l.setStartDate(dto.getStartDate());
        l.setEndDate(dto.getEndDate());
        l.setType(dto.getType());
        l.setReason(dto.getReason());
        l.setStatus("PENDING");
        LeaveRequest saved = leaveRepo.save(l);
        LeaveRequestDto res = new LeaveRequestDto();
        res.setId(saved.getId());
        res.setEmployeeId(saved.getEmployee().getId());
        res.setStartDate(saved.getStartDate());
        res.setEndDate(saved.getEndDate());
        res.setType(saved.getType());
        res.setReason(saved.getReason());
        res.setStatus(saved.getStatus());
        return res;
    }

    @Override
    public LeaveRequestDto approve(Long id) {
        LeaveRequest l = leaveRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        l.setStatus("APPROVED");
        LeaveRequest saved = leaveRepo.save(l);
        LeaveRequestDto res = new LeaveRequestDto();
        res.setId(saved.getId());
        res.setEmployeeId(saved.getEmployee().getId());
        res.setStartDate(saved.getStartDate());
        res.setEndDate(saved.getEndDate());
        res.setType(saved.getType());
        res.setReason(saved.getReason());
        res.setStatus(saved.getStatus());
        return res;
    }

    @Override
    public LeaveRequestDto reject(Long id) {
        LeaveRequest l = leaveRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        l.setStatus("REJECTED");
        LeaveRequest saved = leaveRepo.save(l);
        LeaveRequestDto res = new LeaveRequestDto();
        res.setId(saved.getId());
        res.setEmployeeId(saved.getEmployee().getId());
        res.setStartDate(saved.getStartDate());
        res.setEndDate(saved.getEndDate());
        res.setType(saved.getType());
        res.setReason(saved.getReason());
        res.setStatus(saved.getStatus());
        return res;
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {
        EmployeeProfile emp = employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return leaveRepo.findByEmployee(emp).stream().map(l -> {
            LeaveRequestDto d = new LeaveRequestDto();
            d.setId(l.getId());
            d.setEmployeeId(l.getEmployee().getId());
            d.setStartDate(l.getStartDate());
            d.setEndDate(l.getEndDate());
            d.setType(l.getType());
            d.setReason(l.getReason());
            d.setStatus(l.getStatus());
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(String teamName, LocalDate start, LocalDate end) {
        List<LeaveRequest> list = leaveRepo.findApprovedOverlappingForTeam(teamName, start, end);
        return list.stream().map(l -> {
            LeaveRequestDto d = new LeaveRequestDto();
            d.setId(l.getId());
            d.setEmployeeId(l.getEmployee() == null ? null : l.getEmployee().getId());
            d.setStartDate(l.getStartDate());
            d.setEndDate(l.getEndDate());
            d.setType(l.getType());
            d.setReason(l.getReason());
            d.setStatus(l.getStatus());
            return d;
        }).collect(Collectors.toList());
    }
}
