package com.test.controller;

import com.test.User.User;
import com.test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterRestController {

    private final UserService userService;

    public RegisterRestController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Cadastro público
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        try {
            if (!dto.getSenha().equals(dto.getConfirmSenha())) {
                return ResponseEntity.badRequest().body("Senhas não conferem");
            }

            User u = userService.register(dto.getName(), dto.getEmail(), dto.getSenha());
            u.setSenha(null); // não retorne a senha
            return ResponseEntity.ok(u);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro interno: " + ex.getMessage());
        }
    }

    // ✅ Listar todos os usuários
    @GetMapping("/users")
    public ResponseEntity<?> listUsers() {
        var users = userService.getAllUsers();
        users.forEach(u -> u.setSenha(null));
        return ResponseEntity.ok(users);
    }

    // ✅ Buscar usuário por ID
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(u -> {
                    u.setSenha(null);
                    return ResponseEntity.ok(u);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Atualizar usuário
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User dto) {
        try {
            User userToUpdate = new User();
            userToUpdate.setName(dto.getName());
            userToUpdate.setEmail(dto.getEmail());
            userToUpdate.setRole(dto.getRole());
            User updated = userService.update(id, userToUpdate);
            updated.setSenha(null);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ✅ Deletar usuário
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    // DTOs
    public static class RegisterDto {
        private String name;
        private String email;
        private String senha;
        private String confirmSenha;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getConfirmSenha() {
            return confirmSenha;
        }

        public void setConfirmSenha(String confirmSenha) {
            this.confirmSenha = confirmSenha;
        }
    }

    
}

