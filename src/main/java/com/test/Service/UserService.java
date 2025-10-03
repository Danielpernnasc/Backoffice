package com.test.service;

import com.test.User.User;
import com.test.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Criar usuário
    public User register(String name, String email, String senha) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setSenha(senha); // para produção use PasswordEncoder
        user.setRole("USER");
        return userRepository.save(user);
    }

    // Listar todos
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar por id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Buscar por email (ajustado!)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Atualizar
    public User update(Long id, User dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setRole(dto.getRole());
        return userRepository.save(existing);
    }

    // Deletar
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

