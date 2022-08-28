package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResUserDto {
    private String fullName;
    private String phone;
    private String region;
    private String roleName;
    private int reliability;
    private String filialName;
}
