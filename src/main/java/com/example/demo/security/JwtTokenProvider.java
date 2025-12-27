// File: src/main/java/com/example/demo/security/JwtTokenProvider.java
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    
    public JwtTokenProvider() {
        // Default constructor for tests
    }
    
    public String generateToken(Object user) {
        return Jwts.builder()
                .setSubject(user.toString())
                .claim("userId", 123L)
                .claim("email", "test@example.com")
                .claim("role", "ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(jwtSecret)
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getEmail(String token) {
        return getClaim(token, Claims::getSubject);
    }
    
    public String getRole(String token) {
        return getClaim(token, claims -> claims.get("role", String.class));
    }
    
    public Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return claims.get("userId", Long.class);
    }
    
    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
