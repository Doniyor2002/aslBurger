package com.example.botservice.repository;

import com.example.botservice.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findAllByOrder_OrderStatus (String orderStatus);
}
