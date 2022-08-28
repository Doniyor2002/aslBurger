package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ProductDto productDto){
        ApiResponse response = productService.add(productDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = productService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/categoryId/{id}")
    public ResponseEntity<?> getAllByCategoryId(@PathVariable Long id){
        ApiResponse response = productService.getAllByCategoryId(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = productService.getOne(id);
        return ResponseEntity.ok().body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto){
        ApiResponse response = productService.update(id,productDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = productService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
