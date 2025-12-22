package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;

import java.util.List;

public interface EmployeeProfileService {

    EmployeeProfile create(EmployeeProfile employee);

    EmployeeProfile update(Long id, EmployeeProfile employee);

    EmployeeProfile getById(Long id);

    List<EmployeeProfile> getByTeam(String teamName);
}
