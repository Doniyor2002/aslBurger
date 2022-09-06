package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.dto.ResDiscountDto;
import com.example.adminservice.entity.Discount;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.entity.Product;
import com.example.adminservice.service.FilialService;
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
@RequestMapping("/filial")
@RequiredArgsConstructor
public class FilialController {
    private final FilialService filialService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody FilialDto filialDto){
        ApiResponse response = filialService.save(filialDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 404).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        ApiResponse response = filialService.getAll(page,size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = filialService.getOne(id);
        return ResponseEntity.status(one.isSuccess() ? 200 : 404).body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody FilialDto filialDto){
        ApiResponse response = filialService.update(id,filialDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = filialService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 404).body(delete);
    }

}
