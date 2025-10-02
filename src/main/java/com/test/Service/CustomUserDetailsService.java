package com.test.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.Repository.UserRepository;

public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
     private final UserRepository repo;

     public CustomUserDetailsService(UserRepository repo) {
         this.repo = repo;
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        String role = u.getRole() == null ? "USER" : u.getRole();
        String granted = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getSenha()) // já deve estar criptografada (BCrypt)
                .authorities(List.of(new SimpleGrantedAuthority(granted)))
                .build();
    }
}
