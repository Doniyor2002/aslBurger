package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResProductDto {
    private String nameUz;
    private String nameRu;
    private String categoryName;
    private Double price;
    private String description;
    private double discountPercentage;
}
