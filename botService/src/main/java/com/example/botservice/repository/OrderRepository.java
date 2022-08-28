package com.example.botservice.repository;

import com.example.botservice.entity.Order;
import com.example.botservice.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByUser_ChatIdAndOrderStatus(Long chatId, OrderStatus orderStatus);
    List<Order> findByUser_ChatId(Long chatId);

}
