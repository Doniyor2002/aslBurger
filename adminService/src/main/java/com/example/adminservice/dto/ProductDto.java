package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    @NotNull
    private String nameUz;
    @NotNull
    private String nameRu;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long photoId;
    @NotNull
    private Double price;
    private String description;
    //chegirma
    private Long discountId;
}
