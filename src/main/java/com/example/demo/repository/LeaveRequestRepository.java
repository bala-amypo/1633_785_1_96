package com.example.demo.repository;

import com.example.demo.model.LeaveRequest;
import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    @Query("select l from LeaveRequest l where l.employee.teamName = :team and l.status = 'APPROVED' and l.startDate <= :end and l.endDate >= :start")
    List<LeaveRequest> findApprovedOverlappingForTeam(@Param("team") String team, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("select l from LeaveRequest l where l.status = 'APPROVED' and l.startDate <= :date and l.endDate >= :date")
    List<LeaveRequest> findApprovedOnDate(@Param("date") LocalDate date);
}
