package com.example.demo.repository;

import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.*;


public interface LeaveRequestRepository {
    Optional<LeaveRequest> findById(Long id);
    LeaveRequest save(LeaveRequest l);
    List<LeaveRequest> findByEmployee(EmployeeProfile e);
    List<LeaveRequest> findApprovedOverlappingForTeam(String team, LocalDate s, LocalDate e);
    List<LeaveRequest> findApprovedOnDate(LocalDate date);
    void deleteById(Long id);
}
