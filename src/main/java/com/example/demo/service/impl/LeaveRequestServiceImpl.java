package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveRequest create(LeaveRequest leaveRequest) {

        if (leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            throw new BadRequestException("Invalid Date Range: Start date after end date");
        }

        leaveRequest.setStatus("PENDING");
        return repository.save(leaveRequest);
    }

    @Override
    public LeaveRequest approve(Long id) {
        LeaveRequest leave = getLeave(id);
        leave.setStatus("APPROVED");
        return repository.save(leave);
    }

    @Override
    public LeaveRequest reject(Long id) {
        LeaveRequest leave = getLeave(id);
        leave.setStatus("REJECTED");
        return repository.save(leave);
    }

    @Override
    public List<LeaveRequest> getByEmployee(Long employeeId) {
        return repository.findByEmployee_Id(employeeId);
    }

    private LeaveRequest getLeave(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
    }
}
