package com.example.botservice.service;

import com.example.botservice.dto.ApiResponse;
import com.example.botservice.entity.Attachment;
import com.example.botservice.repository.AttachmnetRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmnetRepository attachmnetRepository;
    private final Path root = Paths.get("C:\\Users\\Doniyor\\IdeaProjects\\fast-food\\botService\\src\\main\\resources\\upload");
    @SneakyThrows
    public ApiResponse uploadFileSystem(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();
        Attachment save = null;
        while (fileNames.hasNext()) {
            Attachment attachment = new Attachment();
            MultipartFile file = request.getFile(fileNames.next());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            attachment.setUrl(this.root+"\\" + file.getOriginalFilename());
            save = attachmnetRepository.save(attachment);
        }
        return ApiResponse.builder().succes(true).message(save.getFileName() + " nomli fayl saqlandi").build();
    }

//    @SneakyThrows
//    public ApiResponse uploadDB(MultipartHttpServletRequest request) {
//        Iterator<String> fileNames = request.getFileNames();
//        while (fileNames.hasNext()) {
//            MultipartFile file = request.getFile(fileNames.next());
//            if (!file.isEmpty()) {
//                //info
//                Attachment attachment = new Attachment();
//                attachment.setSize(file.getSize());
//                attachment.setContentType(file.getContentType());
//                attachment.setFileName(file.getOriginalFilename());
//                attachment.setBytes(file.getBytes());
//                attachmentRepository.save(attachment);
//            }
//        }
//
//        return ApiResponse.builder().success(true).message("Chotki").build();
//    }

//    public ResponseEntity<?> downloadDB(UUID id) {
        //info
//        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id", id));
//
//        AttachmentContent byAttachment = attachmentContentRepository.findByAttachment(attachment);
//        AttachmentContent byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
//
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(attachment.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
//                .body(byAttachment.getBytes());
//    }
//        return ResponseEntity.ok();
}
