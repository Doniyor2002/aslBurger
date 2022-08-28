package com.example.adminservice.repository;

import com.example.adminservice.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findAllByOrder_OrderStatus (String orderStatus);
}
