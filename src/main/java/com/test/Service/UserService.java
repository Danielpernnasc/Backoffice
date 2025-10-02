package com.test.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.User.User;


@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    public List<User> getAllUsers() { return users; }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public User createUser(User user) {
        user.setId(idCounter++);
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User user) {
        User existing = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existing.setNome(user.getNome());
        existing.setEmail(user.getEmail());
        existing.setSenha(user.getSenha());
        return existing;
    }

    public void deleteUser(Long id) {
        users.removeIf(u -> Objects.equals(u.getId(), id));
    }
}

