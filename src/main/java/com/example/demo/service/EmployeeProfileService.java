// File: src/main/java/com/example/demo/service/EmployeeProfileService.java
package com.example.demo.service;

import com.example.demo.dto.EmployeeProfileDto;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfileDto create(EmployeeProfileDto dto);
    EmployeeProfileDto update(Long id, EmployeeProfileDto dto);
    EmployeeProfileDto getById(Long id);
    void deactivate(Long id);
    List<EmployeeProfileDto> getByTeam(String teamName);
    List<EmployeeProfileDto> getAll();
}
