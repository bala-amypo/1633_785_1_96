package com.example.demo.service;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
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
    public LeaveRequestDto create(LeaveRequestDto dto) {

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployeeId(dto.getEmployeeId());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setStatus("PENDING");

        return toDto(leaveRequestRepository.save(leave));
    }

    @Override
    public LeaveRequestDto approve(Long id) {

        LeaveRequest leave = getLeave(id);
        leave.setStatus("APPROVED");
        return toDto(leaveRequestRepository.save(leave));
    }

    @Override
    public LeaveRequestDto reject(Long id) {

        LeaveRequest leave = getLeave(id);
        leave.setStatus("REJECTED");
        return toDto(leaveRequestRepository.save(leave));
    }

    @Override
    public List<LeaveRequestDto> getByEmployee(Long employeeId) {

        return leaveRequestRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LeaveRequest getLeave(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
    }

    private LeaveRequestDto toDto(LeaveRequest leave) {
        return new LeaveRequestDto(
                leave.getId(),
                leave.getEmployeeId(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getStatus()
        );
    }
}
