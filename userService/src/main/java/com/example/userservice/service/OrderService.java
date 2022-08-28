package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.OrderDto;
import com.example.userservice.dto.ProductWQuantity;
import com.example.userservice.dto.ResOrderDto;
import com.example.userservice.entity.*;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
//@Slf4j
@RequiredArgsConstructor
public class OrderService {
    //@Autowired
    private final OrderRepository orderRepository;
    //@Autowired
    private final UserRepository userRepository;
    //@Autowired
    private final FilialRepository filialRepository;
    //@Autowired
    private final DetailRepository detailRepository;
    //@Autowired
    private final OrderHistoryRepository orderHistoryRepository;
    private final AddressRepository addressRepository;

    public ApiResponse add(OrderDto orderDto) {
        Order order=new Order();
        order.setStatus(true);
        order.setDeleted(false);
        order.setFilial(filialRepository.findById(orderDto.getFilialId()).orElseThrow(() -> new RuntimeException("Filial Not Found")));
        order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));
        order.setPayType(orderDto.getPayType());
        order.setOrderType(orderDto.getOrderType());
        order.setDetailList(detailRepository.findAllById(orderDto.getDetailList()));

        Address address=new Address();
        address.setLat(orderDto.getAddress().getLat());
        address.setLon(orderDto.getAddress().getLon());
        address.setTarget(orderDto.getAddress().getTarget());
        address.setStatus(true);
        address.setDeleted(false);
        address.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));
        addressRepository.save(address);

        order.setAddress(address);
        User user1 = userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        List<Address> addressList = user1.getAddressList();
        addressList.add(address);
        user1.setAddressList(addressList);
        userRepository.save(user1);

        Double price=0D;

        List<Detail> details = detailRepository.findAllById(orderDto.getDetailList());
        for (Detail detail : details) {
            price=price+(detail.getProduct().getPrice()*detail.getQuantity());
        }
        order.setSumma(price);

        User user = order.getUser();
        if (user.getReliabilty()>=3){
            order.setOrderStatus(OrderStatus.APPROVED);
        }else order.setOrderStatus(OrderStatus.NEW);
        Order save = orderRepository.save(order);

        OrderHistory orderHistory=new OrderHistory();
        orderHistory.setFilial(save.getFilial());
        orderHistory.setCustomer(save.getUser());
        orderHistory.setOrder(save);
        orderHistoryRepository.save(orderHistory);

        return ApiResponse.builder().message("Successful").success(true).build();
    }
    public ResOrderDto orderToDto(Order order){
        ResOrderDto resOrderDto=new ResOrderDto();
        resOrderDto.setId(order.getId());
        resOrderDto.setOrderStatus(order.getOrderStatus());
        resOrderDto.setOrderType(order.getOrderType());
        List<ProductWQuantity> productWQuantities=new ArrayList<>();
        for (Detail detail : order.getDetailList()) {
            ProductWQuantity productWQuantity=new ProductWQuantity();
            productWQuantity.setProductName(detail.getProduct().getNameUz());
            productWQuantity.setQuantity((short) detail.getQuantity());
            productWQuantities.add(productWQuantity);
        }
        resOrderDto.setProductWQuantities(productWQuantities);
        return resOrderDto;
    }
    public ApiResponse delete(Long id) {
        Optional<Order>optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return ApiResponse.builder().success(false).message("Order Not Found").build();
        }
        Order order = optionalOrder.get();
        orderRepository.delete(order);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
    public ApiResponse edit(Long id,OrderStatus orderStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return ApiResponse.builder().success(false).message("Order Not Found").build();
        }
        Order order = optionalOrder.get();
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return ApiResponse.builder().message("Edited").success(true).build();
    }

    public ApiResponse getOne(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order Not Found"));
        return ApiResponse.builder().success(true).message("There").data(orderToDto(order)).build();
    }

    public Page<ResOrderDto> toDTOPage(Page<Order> orders) {
        List<ResOrderDto> collect =orders.stream().map(this::orderToDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    public ApiResponse getAll(OrderStatus orderStatus,Long userId) {
        Pageable pageable=PageRequest.of(0,10);
        Page<Order> data=null;
        if (orderStatus.equals(null)&&userId==0){//for Admin
            data = orderRepository.findAll(pageable);
        } else if (!(userId == 0)) {
            data=orderRepository.findAllByUserId(userId,pageable);//For user
        }
        data=orderRepository.findAllByOrderStatus(orderStatus,pageable);//For Operator
        return ApiResponse.builder().success(true).message("There").data(toDTOPage(data)).build();
    }
}
