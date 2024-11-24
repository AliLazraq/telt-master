package com.example.telt_project.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "806cf9f0b8a9bcc5cd2b90c3d639622aecacd00fa4154d21229650d941f2bc67afd6dfefa534ebb51725f9bc0e6d46cda4928839c21e06a73c8b4cf08c400b8b"; // Use a 256-bit key
    private static final int EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key;

    public JwtUtil() {
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate JWT Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // Invalid token
        }
    }

    // Extract Email from Token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
