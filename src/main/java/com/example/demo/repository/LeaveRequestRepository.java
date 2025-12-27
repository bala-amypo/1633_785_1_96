// File: src/main/java/com/example/demo/repository/LeaveRequestRepository.java
package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);
    @Query("SELECT lr FROM LeaveRequest lr JOIN lr.employee e WHERE e.teamName = :teamName AND lr.status = 'APPROVED' AND " +
           "((:startDate BETWEEN lr.startDate AND lr.endDate) OR (:endDate BETWEEN lr.startDate AND lr.endDate) OR " +
           "(lr.startDate BETWEEN :startDate AND :endDate))")
    List<LeaveRequest> findApprovedOverlappingForTeam(String teamName, LocalDate startDate, LocalDate endDate);
    
    List<LeaveRequest> findApprovedOnDate(LocalDate date);
}
