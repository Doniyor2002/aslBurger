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
public class FilialDto {
    @NotNull
    private String nameUz;
    @NotNull
    private String nameRu;
    //FilialDto da startTime va endTime HH:mm ko`rinishda yoziladi
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;
    @NotNull
    private Long addressId;
}
