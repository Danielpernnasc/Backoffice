package com.test.service;

import com.test.models.User; // Ensure this points to the correct package where the User class is defined

// Verify that the User class exists in the specified package
// If the package is incorrect, update the import statement to the correct package
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() { return userRepository.findAll(); }

    public Optional<User> findById(Long id) { return userRepository.findById(id); }

    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }

    public User save(User user) { return userRepository.save(user); }

    public void deleteById(Long id) { userRepository.deleteById(id); }

    public User updateById(Long id, User payload){
        return userRepository.findById(id).map(u -> {
            u.setName(payload.getName());
            u.setEmail(payload.getEmail());
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
