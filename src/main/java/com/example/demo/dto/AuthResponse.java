// Update: src/main/java/com/example/demo/dto/AuthResponse.java
package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private Long userId;
    
    public AuthResponse() {} // Add default constructor
    
    public AuthResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
    
    // ... existing getters/setters
}
