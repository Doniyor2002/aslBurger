package com.example.botservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean succes;

    public ApiResponse(String message, boolean succes) {
        this.message = message;
        this.succes = succes;
    }
}
