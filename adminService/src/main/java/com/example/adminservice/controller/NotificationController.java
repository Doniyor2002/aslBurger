package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.NotificationDto;
import com.example.adminservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody NotificationDto notificationDto){
        ApiResponse response = notificationService.save(notificationDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        ApiResponse response = notificationService.getAll(page,size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = notificationService.getOne(id);
        return ResponseEntity.status(one.isSuccess() ? 200 : 404).body(one);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = notificationService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 404).body(delete);
    }
}
