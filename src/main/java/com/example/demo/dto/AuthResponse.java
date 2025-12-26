package com.example.demo.dto;

public class AuthResponse {
    private Long userId;
    private String token;

    public AuthResponse(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public String getToken() { return token; }
}
