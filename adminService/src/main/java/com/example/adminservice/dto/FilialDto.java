package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilialDto {
    private String nameUz;
    private String nameRu;
    //FilialDto da startTime va endTime HH:mm ko`rinishda yoziladi
    private String startTime;
    private String endTime;
    private Long addressId;
}
