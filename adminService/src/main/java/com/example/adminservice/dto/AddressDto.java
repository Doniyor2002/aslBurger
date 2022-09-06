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
public class AddressDto {
    private String nameUz;
    private String nameRu;
    @NotNull
    private Double lat;
    @NotNull
    private Double lon;
    @NotNull
    private String target;
}
