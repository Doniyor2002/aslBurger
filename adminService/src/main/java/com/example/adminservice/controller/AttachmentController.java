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
    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.uploadFileSystem(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

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
