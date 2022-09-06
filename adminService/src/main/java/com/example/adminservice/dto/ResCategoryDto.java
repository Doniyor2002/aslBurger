package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResCategoryDto {
    private String nameUz;
    private String nameRu;
    private String parentName;
    private List<String> filialName;
}
