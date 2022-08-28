package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private String nameUz;
    private String nameRu;
    private Long categoryId;
    private Long photoId;
    private Double price;
    private String description;
    //chegirma
    private Long discountId;
}
