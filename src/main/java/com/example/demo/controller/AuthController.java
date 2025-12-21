package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return authService.authenticate(req);
    }

    @PostMapping("/register")
    public void register() {}
}
