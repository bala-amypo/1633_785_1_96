package com.example.demo.controller;

import com.example.demo.model.LeaveRequest;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.EmployeeProfileRepository;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class LeaveController {
    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveController(LeaveRequestRepository leaveRepo, EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/leaves")
    public String list(Model model) {
        model.addAttribute("leaves", leaveRepo.findAll());
        return "leaves";
    }

    @PostMapping("/leaves")
    public String add(@RequestParam Long employeeId, @RequestParam String start, @RequestParam String end) {
        EmployeeProfile e = employeeRepo.findById(employeeId).orElse(null);
        if (e == null) return "redirect:/leaves";
        LeaveRequest l = new LeaveRequest();
        l.setEmployee(e);
        l.setStartDate(LocalDate.parse(start));
        l.setEndDate(LocalDate.parse(end));
        l.setStatus("PENDING");
        leaveRepo.save(l);
        return "redirect:/leaves";
    }
}
