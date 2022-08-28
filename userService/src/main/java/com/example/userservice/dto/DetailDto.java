package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long productId;
    @NotNull
    private short quantity;
}
