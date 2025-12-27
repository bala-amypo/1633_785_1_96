package com.example.demo.service.impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.model.LeaveStatus;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {
        LeaveRequest entity = convertToEntity(dto);
        LeaveRequest saved = leaveRequestRepository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public LeaveRequestDto getById(Long id) {
        return leaveRequestRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public List<LeaveRequestDto> getByStatus(String status) {
        LeaveStatus leaveStatus = LeaveStatus.valueOf(status.toUpperCase());
        return leaveRequestRepository.findByStatus(leaveStatus)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Fix other enum conversion methods similarly
    private LeaveRequestDto convertToDto(LeaveRequest entity) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private LeaveRequest convertToEntity(LeaveRequestDto dto) {
        LeaveRequest entity = new LeaveRequest();
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
`