package com.test.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.test.Dto.LoginRequest;
import com.test.Security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (!"admin@email.com".equals(request.getEmail())
                || !"123456".equals(request.getSenha())) {
            return ResponseEntity.status(401).body(Map.of("error","Credenciais inválidas"));
        }
        String token = jwtUtil.generateToken(request.getEmail(), "ADMIN");
        return ResponseEntity.ok(Map.of(
                "token", token,
                "tipo", "Bearer",
                "email", request.getEmail(),
                "role", "ADMIN"
        ));
    }

    @PutMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String,String> body) {
        String email = body.get("email");
        if (email == null) {
            return ResponseEntity.badRequest().body(Map.of("error","email obrigatório"));
        }
        String token = jwtUtil.generateToken(email, "ADMIN");
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String,String> body) {
        // Placeholder: substituir por lógica real (UserService / repository)
        if (!"123456".equals(body.get("oldSenha"))) {
            return ResponseEntity.status(401).body(Map.of("error","Senha antiga inválida"));
        }
        // nova senha em body.get("newSenha") (apenas descartado aqui)
        return ResponseEntity.ok(Map.of("status","Senha alterada"));
    }
}