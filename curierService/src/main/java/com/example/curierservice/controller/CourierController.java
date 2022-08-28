package com.example.curierservice.controller;

import com.example.curierservice.client.FeignUser;
import com.example.curierservice.dto.ApiResponse;
import com.example.curierservice.dto.OrderHistoryDto;
import com.example.curierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    private final FeignUser feignUser;

    @PutMapping("/online/{id}")
    public ResponseEntity<?> editOnline(@PathVariable Long id,@RequestParam Boolean online){
       return feignUser.editOnline(id,online);
    }
    @GetMapping
    public ResponseEntity<?> getOrders (@RequestParam Long id) {
        ApiResponse response=courierService.getAll(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrder(@PathVariable Long id){
        return feignUser.getOne(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editOrder(@PathVariable Long id,@RequestBody OrderHistoryDto orderHistoryDto){
        return feignUser.edit(id,orderHistoryDto);
    }
}
