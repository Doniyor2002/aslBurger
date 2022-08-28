package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.entity.Support;
import com.example.userservice.entity.enums.SupportType;
import com.example.userservice.repository.SupportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SupportService {
    @Autowired
    SupportRepository supportRepository;

    public ApiResponse add(Support support) {
        supportRepository.save(support);
        return ApiResponse.builder().success(true).message("Added").build();
    }

    public ApiResponse delete(Long id) {
        try {
            Support support = supportRepository.findById(id).get();
            supportRepository.delete(support);
            return ApiResponse.builder().message("Deleted").success(true).build();
        } catch (Exception e) {
            log.error("error -> " + e);
        }
        return ApiResponse.builder().success(false).build();
    }

    public ApiResponse getAll(SupportType supportType) {
        Pageable pageable= PageRequest.of(0,10);
        Page<Support> data=null;
        if (supportType.equals("null")) data=supportRepository.findAll(pageable);
        else data=supportRepository.findAllBySupportType(supportType,pageable);
        return ApiResponse.builder().success(true).message("There").data(data).build();
    }

    public ApiResponse getOne(Long id) {
        Support support = supportRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return ApiResponse.builder().data(support).message("There").success(true).build();
    }
}
