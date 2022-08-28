package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.entity.OrderHistory;
import com.example.adminservice.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;
    public ApiResponse getAllByOrderStatus(String orderStatus) {
        List<OrderHistory> allByOrderStatus = orderHistoryRepository.findAllByOrder_OrderStatus(orderStatus);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(allByOrderStatus).build();
    }

}
