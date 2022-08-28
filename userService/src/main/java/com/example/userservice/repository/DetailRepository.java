package com.example.userservice.repository;

import com.example.userservice.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetailRepository extends JpaRepository<Detail ,Long> {
}
