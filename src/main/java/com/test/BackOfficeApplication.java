package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.Repository.UserRepository;
import com.test.User.User;

@SpringBootApplication
@ComponentScan(basePackages = {"com.test", "com.test.controller"})
public class BackOfficeApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public BackOfficeApplication(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    public static void main(String[] args) {
        SpringApplication.run(BackOfficeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@email.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@email.com");
            admin.setName("Admin");
            admin.setSenha(encoder.encode("123456"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("Admin criado com sucesso!");
        }
    }
}


