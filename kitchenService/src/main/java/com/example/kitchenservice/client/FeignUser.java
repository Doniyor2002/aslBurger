package com.example.kitchenservice.client;

import com.example.kitchenservice.entity.enums.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userService",url = "http://localhost:8081")
public interface FeignUser {
    @GetMapping("/api/user/order")
    ResponseEntity<?> getOrder(@RequestParam OrderStatus orderStatus);

    @PutMapping("/api/user/order/{id}")
    ResponseEntity<?> editOrder(@PathVariable Long id, OrderStatus orderStatus);
}
