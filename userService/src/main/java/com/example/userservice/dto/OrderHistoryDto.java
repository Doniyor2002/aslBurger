package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistoryDto {
    private Long courierId;
    private int reliability;
    private String description;
    @CreationTimestamp
    private Timestamp timestamp;

}
