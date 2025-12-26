package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeProfileRepository employeeRepo;

    public EmployeeController(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/employees")
    public String list(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
        return "employees";
    }

    @PostMapping("/employees")
    public String add(@RequestParam String fullName, @RequestParam String email) {
        EmployeeProfile e = new EmployeeProfile();
        e.setFullName(fullName);
        e.setEmail(email);
        employeeRepo.save(e);
        return "redirect:/employees";
    }
}
