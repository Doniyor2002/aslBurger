package com.example.userservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    @NotNull
    private String phone;
    @NotNull
    private String fullName;
    @NotNull
    private String password;
    @NotNull
    private String secondPassword;
    @NotNull
    private String region;
    @NotNull
    private boolean agreement;
}
