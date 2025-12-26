package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;
@service
public interface EmployeeProfileService {
    EmployeeProfile getEmployeeById(Long id);
}
