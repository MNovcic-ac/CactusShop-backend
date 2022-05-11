package com.example.cactusshop.repository;

import com.example.cactusshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByContactEmail(String email);
}
