package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}