package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    List<EmployeeProfile> findByTeamNameAndActiveTrue(String teamName);
}

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);
    List<LeaveRequest> findApprovedOverlappingForTeam(String team, LocalDate s, LocalDate e);
    List<LeaveRequest> findApprovedOnDate(LocalDate date);
}

public interface TeamCapacityConfigRepository extends JpaRepository<TeamCapacityConfig, Long> {
    Optional<TeamCapacityConfig> findByTeamName(String team);
}

public interface CapacityAlertRepository extends JpaRepository<CapacityAlert, Long> {
    List<CapacityAlert> findByTeamNameAndDateBetween(String team, LocalDate s, LocalDate e);
}

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
