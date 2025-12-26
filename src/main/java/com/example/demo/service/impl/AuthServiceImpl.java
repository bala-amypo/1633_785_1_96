package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(UserAccountRepository userRepo, BCryptPasswordEncoder encoder, JwtTokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResponse authenticate(AuthRequest req) {
        Optional<UserAccount> opt = userRepo.findByEmail(req.getEmail());
        if (opt.isEmpty()) throw new BadRequestException("User not found");
        UserAccount user = opt.get();
        if (!encoder.matches(req.getPassword(), user.getPassword())) throw new BadRequestException("Invalid password");
        String token = tokenProvider.generateToken(user);
        AuthResponse resp = new AuthResponse();
        resp.setToken(token);
        resp.setUserId(user.getId());
        return resp;
    }
}
