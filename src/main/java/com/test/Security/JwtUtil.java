package com.test.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {
    private static final String SECRET = "chaveSecretaMinimaParaHS256_SubstituaDepois12345";
    private static final long EXP_MS = 60 * 60 * 1000; // 1h

    private Key key() { return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); }

    public String generate(String email, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXP_MS))
                .signWith(key(), SignatureAlgorithm.HS384)
                .compact();
    }

    private Claims claims(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) { return claims(token).getSubject(); }
    public String extractRole(String token) { return claims(token).get("role", String.class); }
}