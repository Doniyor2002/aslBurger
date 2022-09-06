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
public class UserDto {
    @NotNull
    private String phone;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private Long filialId;
}
