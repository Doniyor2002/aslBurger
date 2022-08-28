package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.OrderDto;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<?> addOrder(@Valid @RequestBody OrderDto orderDto){
        ApiResponse response=orderService.add(orderDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        ApiResponse response=orderService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")//feignClient operator courier
    //done
    public ResponseEntity<?> editOrder(@PathVariable Long id, @RequestParam OrderStatus orderStatus){
        ApiResponse response=orderService.edit(id,orderStatus);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneOrder(@PathVariable Long id){
        ApiResponse response=orderService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping//feignClient operator
    //done
    public ResponseEntity<?> getAllOrder(@RequestParam(defaultValue = "null",required = false) OrderStatus orderStatus,//todo
                                         @RequestParam(defaultValue = "0",required = false) Long userId){
        ApiResponse response=orderService.getAll(orderStatus,userId);
        return ResponseEntity.ok(response);
    }
}

