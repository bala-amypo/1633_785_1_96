// File: src/main/java/com/example/demo/service/AuthService.java
package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
}
