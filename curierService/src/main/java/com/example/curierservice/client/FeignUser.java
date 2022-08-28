package com.example.curierservice.client;

import com.example.curierservice.dto.OrderHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userService",url = "http://localhost:8081")
public interface FeignUser {
    @PutMapping("/api/user/user/{id}")
    ResponseEntity<?> editOnline(@PathVariable Long id, @RequestParam Boolean online);

    @GetMapping("/api/user/history/{id}")
    ResponseEntity<?> getOne(@PathVariable Long id);

    @PutMapping("/api/user/history/{id}")
    ResponseEntity<?> edit(@PathVariable Long id, @RequestBody OrderHistoryDto orderHistoryDto);
}
