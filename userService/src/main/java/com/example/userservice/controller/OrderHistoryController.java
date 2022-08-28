package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.OrderHistoryDto;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @GetMapping
    public ResponseEntity<?> getAllOrderHistory(@RequestParam(required = false,defaultValue = "null")OrderStatus orderStatus){
        ApiResponse response=orderHistoryService.getAll(orderStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneHistory(@PathVariable Long id){
        ApiResponse response=orderHistoryService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editOrderHistory(@PathVariable Long id,@RequestBody OrderHistoryDto orderHistoryDto){
        ApiResponse response=orderHistoryService.edit(id,orderHistoryDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}

