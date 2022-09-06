package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.DiscountDto;
import com.example.adminservice.repository.DiscountRepository;
import com.example.adminservice.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody DiscountDto discountDto){
        ApiResponse response = discountService.save(discountDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        ApiResponse response = discountService.getAll(page,size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = discountService.getOne(id);
        return ResponseEntity.status(one.isSuccess() ? 200 : 404).body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DiscountDto discountDto){
        ApiResponse response = discountService.update(id,discountDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = discountService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 404).body(delete);
    }

}
