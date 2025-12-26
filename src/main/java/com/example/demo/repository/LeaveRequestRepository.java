package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    @Query("""
        select l from LeaveRequest l
        where l.teamName = :teamName
          and l.status = 'APPROVED'
          and l.startDate <= :endDate
          and l.endDate >= :startDate
    """)
    List<LeaveRequest> findApprovedOverlappingForTeam(
            String teamName,
            LocalDate startDate,
            LocalDate endDate
    );
}
