package com.example.telt_project.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "bf723f8355da51cede14b6fade2a0c7f41fb9b66143187cbbd085e8217e25ef906ae3407a8ae670a311c7f944ea5c8507c2f49fc3dee9a2ca55d511bd8ba6894"; // Use a 256-bit key
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
