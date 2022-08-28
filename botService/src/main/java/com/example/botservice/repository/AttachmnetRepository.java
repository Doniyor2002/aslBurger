package com.example.botservice.repository;


import com.example.botservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmnetRepository extends JpaRepository<Attachment,Long> {
}
