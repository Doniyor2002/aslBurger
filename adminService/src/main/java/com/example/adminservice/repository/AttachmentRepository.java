package com.example.adminservice.repository;

import com.example.adminservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByFileNameContainingIgnoreCase (String fileName);
}
