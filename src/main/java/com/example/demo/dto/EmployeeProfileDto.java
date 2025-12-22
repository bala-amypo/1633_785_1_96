package com.example.demo.dto;

public class EmployeeProfileDto {

    private Long id;
    private String name;
    private String email;
    private String teamName;
    private Integer dailyCapacity;

    public EmployeeProfileDto() {
    }

    public EmployeeProfileDto(Long id, String name, String email,
                              String teamName, Integer dailyCapacity) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.teamName = teamName;
        this.dailyCapacity = dailyCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(Integer dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }
}
