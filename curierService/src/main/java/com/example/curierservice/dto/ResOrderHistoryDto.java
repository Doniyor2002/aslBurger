package com.example.curierservice.dto;

import com.example.curierservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResOrderHistoryDto {
    private String filialName;
    private String customerName;
    private String courierName;
    private Long orderId;
    private Address address;
}
