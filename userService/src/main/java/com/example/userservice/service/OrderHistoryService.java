package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.OrderHistoryDto;
import com.example.userservice.dto.ProductWQuantity;
import com.example.userservice.dto.ResOrderHistoryDto;
import com.example.userservice.entity.Detail;
import com.example.userservice.entity.OrderHistory;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.repository.OrderHistoryRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final UserRepository userRepository;

    public ApiResponse getAll(OrderStatus orderStatus) {
        Page<OrderHistory> histories=null;
        if (orderStatus.equals(null)){
            histories = orderHistoryRepository.findAll(PageRequest.of(0, 10));
        }else {
            histories = orderHistoryRepository.findAllByOrder_OrderStatus(orderStatus,PageRequest.of(0,10));
        }

        return ApiResponse.builder().success(true).message("There").data(toDTOPage(histories)).build();
    }

    public ResOrderHistoryDto orderHistoryToDto(OrderHistory orderHistory){
        ResOrderHistoryDto resOrderHistoryDto=new ResOrderHistoryDto();
        resOrderHistoryDto.setUserName(orderHistory.getCustomer().getFullName());
        resOrderHistoryDto.setReliability((long) orderHistory.getCustomer().getReliabilty());
        resOrderHistoryDto.setPhoneNumber(orderHistory.getCustomer().getPhone());
        resOrderHistoryDto.setAddress(orderHistory.getOrder().getAddress());

        List<Detail> detailList = orderHistory.getOrder().getDetailList();
        List<ProductWQuantity> productList=new ArrayList<>();
        for (Detail detail : detailList) {
            ProductWQuantity productWQuantity=new ProductWQuantity();
            productWQuantity.setProductName(detail.getProduct().getNameUz());
            productWQuantity.setQuantity((short) detail.getQuantity());
            productList.add(productWQuantity);
        }

        resOrderHistoryDto.setProductList(productList);

        return resOrderHistoryDto;
    }

    public Page<ResOrderHistoryDto> toDTOPage(Page<OrderHistory> orderHistories) {
        List<ResOrderHistoryDto> collect =orderHistories.stream().map(this::orderHistoryToDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    public ApiResponse getOne(Long id) {
        OrderHistory orderHistory = orderHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        return ApiResponse.builder().data(orderHistoryToDto(orderHistory)).message("There").success(true).build();
    }

    public ApiResponse edit(Long id, OrderHistoryDto orderHistoryDto) {
        OrderHistory orderHistory = orderHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        try {
//            orderHistory.setReliability(orderHistoryDto.getReliability());
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        try {
            orderHistory.setDescription(orderHistoryDto.getDescription());
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        try {
            orderHistory.setCourier(userRepository.findById(orderHistoryDto.getCourierId()).orElseThrow(() -> new RuntimeException("Not Found")));
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        try {
            orderHistory.setDeliveredTime(orderHistoryDto.getTimestamp());
            orderHistoryRepository.save(orderHistory);
        }catch (Exception e){
            log.error(String.valueOf(e));
        }

        return ApiResponse.builder().success(true).message("Edited").build();

    }
}
