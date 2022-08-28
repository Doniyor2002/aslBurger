package com.example.userservice.dto;

import com.example.userservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResOrderHistoryDto {
    private String userName;
    private Long reliability;
    private String phoneNumber;
    private List<ProductWQuantity> productList;
    private Address address;
}
