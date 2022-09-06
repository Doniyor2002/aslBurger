package com.example.adminservice.controller;

import com.example.adminservice.dto.AddressDto;
import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.dto.ResCategoryDto;
import com.example.adminservice.entity.Address;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.save(categoryDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        ApiResponse response = categoryService.getAll(page,size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = categoryService.getOne(id);
        return ResponseEntity.status(one.isSuccess() ? 200 : 404).body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.update(id,categoryDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = categoryService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 404).body(delete);
    }


}
