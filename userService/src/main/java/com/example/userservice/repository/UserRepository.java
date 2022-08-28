package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from users u where upper(u.role.nameUz) like upper(concat('%', ?1, '%')) and u.online = ?2")
    Page<User> findAllByRole_NameContainingIgnoreCaseAndOnline(String role, Boolean online, Pageable pageable);
}
