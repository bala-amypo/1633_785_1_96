package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
@service
public interface AuthService {
    AuthResponse authenticate(AuthRequest req);
}
