package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.entity.Support;
import com.example.userservice.entity.enums.SupportType;
import com.example.userservice.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
public class SupportController {
    @Autowired
    SupportService supportService;

    @PostMapping
    public ResponseEntity<?> addSupport(@RequestBody Support support){
        ApiResponse response=supportService.add(support);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupport(@PathVariable Long id){
        ApiResponse response=supportService.delete(id);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAllSupport(@RequestParam(defaultValue = "null",required = false) SupportType supportType){
        ApiResponse response=supportService.getAll(supportType);
        return ResponseEntity.ok(response);
    }//todo
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneSupport(@PathVariable Long id){
        ApiResponse response=supportService.getOne(id);
        return ResponseEntity.ok(response);
    }
}
