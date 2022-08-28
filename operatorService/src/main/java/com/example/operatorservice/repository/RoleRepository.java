package com.example.operatorservice.repository;

import com.example.operatorservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByNameUzContainingIgnoreCase(String name);
}
