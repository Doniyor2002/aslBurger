package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResDetailDto {
    private String productName;
    private byte[] photo;
    private short quantity;
    private Double price;
}
