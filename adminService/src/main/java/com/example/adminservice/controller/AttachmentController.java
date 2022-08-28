package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;

    //filesave DB
    @PostMapping("/uploadDB")
    public ResponseEntity<?> saveToDB(MultipartHttpServletRequest request) {
        ApiResponse response = attachmentService.uploadDB(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/downloadDB/{attachmentId}")
    public ResponseEntity<?> downloadDB(@PathVariable(value = "attachmentId") Long id) {
       return attachmentService.downloadDB(id);
    }

    @GetMapping("/qollanma")
    public ResponseEntity<?> qollanma() {
        String fileName = "Qo`llanma";
        return attachmentService.attachmentFromFileName(fileName);
    }

    @GetMapping("/ofertaShartlari")
    public ResponseEntity<?> ofertaSharti() {
        String fileName = "Oferta Shartlari";
        return attachmentService.attachmentFromFileName(fileName);
    }

}
