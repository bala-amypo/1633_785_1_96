package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    @Query("""
        select l from LeaveRequest l
        where l.employee.teamName = :team
          and l.status = 'APPROVED'
          and l.startDate <= :end
          and l.endDate >= :start
    """)
    List<LeaveRequest> findApprovedOverlappingForTeam(
            String team,
            LocalDate start,
            LocalDate end
    );

    @Query("""
        select l from LeaveRequest l
        where l.status = 'APPROVED'
          and :date between l.startDate and l.endDate
    """)
    List<LeaveRequest> findApprovedOnDate(LocalDate date);
}
