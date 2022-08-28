package com.example.adminservice.repository;

import com.example.adminservice.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Long> {
}
