package com.example.demo.security;

import com.example.demo.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {
    private String jwtSecret = "change-this-secret-key-change-this-secret-key-change";

    public JwtTokenProvider() {
    }

    public String generateToken(UserAccount user) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            claims.put("role", user.getRole());
            claims.put("userId", user.getId());
            claims.put("sub", String.valueOf(user.getId()));
            ObjectMapper om = new ObjectMapper();
            String payload = om.writeValueAsString(claims);
            String b64 = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
            String sig = sign(b64);
            return b64 + "." + sig;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean validateToken(String token) {
        try {
            if (token == null) return false;
            String[] parts = token.split("\\.");
            if (parts.length != 2) return false;
            String payload = parts[0];
            String sig = parts[1];
            return sign(payload).equals(sig);
        } catch (Exception ex) {
            return false;
        }
    }

    public String getEmail(String token) {
        Map<String, Object> claims = parseClaims(token);
        return claims.get("email") == null ? null : claims.get("email").toString();
    }

    public String getRole(String token) {
        Map<String, Object> claims = parseClaims(token);
        return claims.get("role") == null ? null : claims.get("role").toString();
    }

    public Long getUserId(String token) {
        Map<String, Object> claims = parseClaims(token);
        if (claims.get("userId") != null) {
            return Long.valueOf(String.valueOf(claims.get("userId")));
        }
        if (claims.get("sub") != null) {
            return Long.valueOf(String.valueOf(claims.get("sub")));
        }
        return null;
    }

    private Map<String, Object> parseClaims(String token) {
        try {
            if (token == null) return new HashMap<>();
            String[] parts = token.split("\\.");
            if (parts.length != 2) return new HashMap<>();
            String b64 = parts[0];
            String json = new String(Base64.getUrlDecoder().decode(b64));
            ObjectMapper om = new ObjectMapper();
            return om.readValue(json, Map.class);
        } catch (Exception ex) {
            return new HashMap<>();
        }
    }

    private String sign(String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256"));
        byte[] sig = mac.doFinal(data.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(sig);
    }
}
