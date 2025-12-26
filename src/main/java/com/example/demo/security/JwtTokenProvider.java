package com.example.demo.security;

import com.example.demo.model.UserAccount;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private String jwtSecret = "default";

    public String generateToken(UserAccount user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims claims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String getEmail(String token) { return claims(token).get("email", String.class); }
    public String getRole(String token) { return claims(token).get("role", String.class); }
    public Long getUserId(String token) { return Long.valueOf(claims(token).getSubject()); }
}
