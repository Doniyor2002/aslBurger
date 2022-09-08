package com.example.botservice.controller;

import com.example.botservice.dto.ApiResponse;
import com.example.botservice.entity.Attachment;
import com.example.botservice.repository.AttachmnetRepository;
import com.example.botservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@CrossOrigin("localhost:8080")
public class AttachmentController {
    private final AttachmnetRepository attachmentRepository;
    private final AttachmentService attachmentService;
    private final Path root = Paths.get("C:\\Users\\Doniyor\\IdeaProjects\\fast_food_bot_full\\src\\main\\resources\\uploads");
//    private final Path root = Paths.get("C:\\Users\\Doniyor\\IdeaProjects\\fast_food_bot_full\\src\\main\\resources\\uploads");
    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.uploadFileSystem(request);
        return ResponseEntity.status(apiResponse.isSucces() ? 201 : 409).body(apiResponse);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        Path file = root.resolve(attachment.getFileName());
        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(resource);
    }



    //filesave DB
//    @PostMapping("/uploadDB")
//    public ResponseEntity<?> saveToDB(MultipartHttpServletRequest request) {
//        ApiResponse response = attachmentService.uploadDB(request);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/downloadDB/{attachmentId}")
//    public ResponseEntity<?> downloadDB(@PathVariable(value = "attachmentId") UUID id) {
//       return attachmentService.downloadDB(id);
//    }


}
