package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.exception.ResourceNotFoundException;
import com.example.adminservice.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @SneakyThrows
    public ApiResponse uploadDB(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (!file.isEmpty()) {
                //info
                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setFileName(file.getOriginalFilename());
                attachment.setBytes(file.getBytes());
                attachmentRepository.save(attachment);
            }
        }
        return ApiResponse.builder().success(true).message("Chotki").build();
    }

    public ResponseEntity<?> downloadDB(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id", id));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(attachment.getBytes());
    }

    public ResponseEntity<?> attachmentFromFileName(String fileName) {
        List<Attachment> qollanma = attachmentRepository.findAllByFileNameContainingIgnoreCase(fileName);
        Attachment attachment = qollanma.get(0);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(attachment.getBytes());
    }

}
