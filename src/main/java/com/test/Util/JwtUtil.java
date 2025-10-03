package com.test.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // use uma string de pelo menos 32 caracteres (256 bits) — ideal vir de application.properties
    private static final String SECRET_STR = "uma_chave_super_secreta_com_pelo_menos_32_chars!";
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_STR.getBytes());
    private final long expirationMs = 1000L * 60 * 60; // 1 hora

    public String generateToken(String subject, String role) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        Object r = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("role");
        return r == null ? null : r.toString();
    }
}
