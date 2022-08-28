package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    // chala tugamagan
    private final FilialService filialService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody FilialDto filialDto){
        ApiResponse response = filialService.save(filialDto);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = filialService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = filialService.getOne(id);
        return ResponseEntity.ok().body(one);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = filialService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
