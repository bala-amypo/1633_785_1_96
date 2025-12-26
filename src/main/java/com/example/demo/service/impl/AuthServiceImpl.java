package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public AuthServiceImpl(UserAccountRepository r, BCryptPasswordEncoder e, JwtTokenProvider j) {
        this.repo = r;
        this.encoder = e;
        this.jwt = j;
    }

    public AuthResponse authenticate(AuthRequest req) {
        UserAccount user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }
        return new AuthResponse(jwt.generateToken(user), user.getId());
    }
}
