package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public UserAccount login(@RequestParam String username,
                             @RequestParam String password) {
        return authService.login(username, password);
    }
}
