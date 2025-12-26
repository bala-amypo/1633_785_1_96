package com.example.demo.repository;

import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.*;

public interface EmployeeProfileRepository {
    Optional<EmployeeProfile> findById(Long id);
    List<EmployeeProfile> findByTeamNameAndActiveTrue(String team);
    List<EmployeeProfile> findAll();
    EmployeeProfile save(EmployeeProfile e);
}
