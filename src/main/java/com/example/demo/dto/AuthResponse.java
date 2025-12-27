// File: src/main/java/com/example/demo/dto/AuthResponse.java
package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private Long userId;
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
