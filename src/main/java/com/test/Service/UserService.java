package com.test.Service;
import org.springframework.stereotype.Service;

import com.test.Repository.UserRepository;
import com.test.User.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> listAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public User create(User u) { 
        // hash de senha deveria ser feito aqui
        return repo.save(u);
    }
    public User update(Long id, User partial) {
        return repo.findById(id).map(existing -> {
            existing.setNome(partial.getNome() != null ? partial.getNome() : existing.getNome());
            existing.setEmail(partial.getEmail() != null ? partial.getEmail() : existing.getEmail());
            if (partial.getSenha() != null) existing.setSenha(partial.getSenha());
            existing.setRole(partial.getRole() != null ? partial.getRole() : existing.getRole());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void delete(Long id) { repo.deleteById(id); }

    public List<User> getAllUsers() {

        return List.of(); 

    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

  

}
