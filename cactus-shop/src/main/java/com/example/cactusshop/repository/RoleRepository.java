package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findRoleByRoleName(String name);
}
