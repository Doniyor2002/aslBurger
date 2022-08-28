package com.example.botservice.repository;

import com.example.botservice.entity.Role;
import com.example.botservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByChatId(Long chatId);
    Optional<User> findByPhone (String username);
    List<User> findAllByRoleNot (Role role);

    List<User> findAllByRole_NameUz (String roleName);
    List<User> findAllByRole_NameRu (String roleName);

}
