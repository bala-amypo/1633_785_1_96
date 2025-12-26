package com.example.demo.repository;

import com.example.demo.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String employeeId,
            LocalDate endDate,
            LocalDate startDate
    );

    List<LeaveRequest> findByTeamNameAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String teamName,
            LocalDate endDate,
            LocalDate startDate
    );
}
