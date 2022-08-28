package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiscountDto {
    private String nameUz;
    private String nameRu;
    private Double percentage;
    private List<Long> productsId;

    public DiscountDto(String nameUz, String nameRu, Double percentage) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.percentage = percentage;
    }
}
