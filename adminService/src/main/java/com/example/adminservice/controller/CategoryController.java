package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.save(categoryDto);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = categoryService.getAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = categoryService.getOne(id);
        return ResponseEntity.ok().body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.update(id,categoryDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = categoryService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
