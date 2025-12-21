package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final UserAccountRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public AuthServiceImpl(UserAccountRepository repo,
                           BCryptPasswordEncoder encoder,
                           JwtTokenProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse authenticate(AuthRequest req) {
        UserAccount user = repo.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new AuthResponse(jwt.generateToken(user.getEmail()));
    }
}
