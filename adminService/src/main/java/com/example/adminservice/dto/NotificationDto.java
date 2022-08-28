package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto {
    private String nameUz;
    private String nameRu;
    private Long userId;
    private String title;
    private String body;
    private Long attachmentId; //attachment
    private boolean hasBot; //true
    private String sendTime;
}
