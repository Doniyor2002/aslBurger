package com.example.operatorservice.controller;

import com.example.operatorservice.client.FeignUser;
import com.example.operatorservice.dto.OrderHistoryDto;
import com.example.operatorservice.entity.enums.OrderStatus;
import com.example.operatorservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final FeignUser feignUser;
    private final RoleRepository roleRepository;


    @GetMapping
    public ResponseEntity<?> getOrders() {
        return feignUser.getOrder(OrderStatus.NEW);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editOrder(@PathVariable Long id, @RequestBody OrderStatus orderStatus) {
       return feignUser.editOrder(id,orderStatus);
    }

    @GetMapping("/ready")
    public ResponseEntity<?> getReadyOrders(){
       return feignUser.getOrder(OrderStatus.READY);
    }

    @GetMapping("/courier")
    public ResponseEntity<?> getCouriers() {
        return feignUser.getAvailableCouriers("COURIER",true);
    }

    @PutMapping("/courier/{id}")
    public ResponseEntity<?> toCourier(@PathVariable Long id, @RequestParam Long courierId) {
        OrderHistoryDto orderHistoryDto=new OrderHistoryDto();
        orderHistoryDto.setCourierId(courierId);
        return feignUser.toCourier(id,orderHistoryDto);
    }
}
