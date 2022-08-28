package com.example.botservice.repository;


import com.example.botservice.entity.Detail;
import com.example.botservice.entity.Order;
import com.example.botservice.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail,Long> {
    List<Detail> findAllByUser_ChatIdAndOrder_OrderStatus(Long chatId, OrderStatus orderStatus);
//    List<Detail> findAllByOrder_Id(Long id);
    Detail findByProduct_IdAndOrder(Long id, Order order);
    @Query(value = "SELECT t FROM Detail t WHERE t.order=:order ")
    List<Detail> findByLevelBetween(@Param("order") Order order);

}
