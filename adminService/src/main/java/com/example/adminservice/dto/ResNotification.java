package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResNotification {
    private String nameUz;
    private String nameRu;
    private String userName;
    private String title;
    private String body;
    private Long attachmentId; //attachment
    private boolean hasBot; //true
    private String sendTime;
}
