package com.example.demo.dto;

import java.time.LocalDate;

public class LeaveRequestDto {

    private Long id;
    private String employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String status;
    private String reason;

    public LeaveRequestDto() {}

    public LeaveRequestDto(Long id, String employeeId, LocalDate startDate,
                           LocalDate endDate, String type,
                           String status, String reason) {
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.reason = reason;
    }

    // getters & setters
}
