package com.example.demo.service;

import com.example.demo.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest create(LeaveRequest leaveRequest);

    LeaveRequest approve(Long id);

    LeaveRequest reject(Long id);

    List<LeaveRequest> getByEmployee(Long employeeId);
}
