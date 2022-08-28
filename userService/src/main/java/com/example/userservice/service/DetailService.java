package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.DetailDto;
import com.example.userservice.dto.ResDetailDto;
import com.example.userservice.entity.Detail;
import com.example.userservice.repository.DetailRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DetailService {

    @Autowired
    DetailRepository detailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse add(DetailDto detailDto) {
        Detail detail = new Detail();
        detail.setUser(userRepository.findById(detailDto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));
        detail.setProduct(productRepository.findById(detailDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found")));
        detail.setQuantity(detailDto.getQuantity());
        detail.setStatus(true);
        detail.setDeleted(false);

        Detail save = detailRepository.save(detail);

        return ApiResponse.builder().message("Added").success(true).data(detailToRes(save)).build();
    }

    public ResDetailDto detailToRes(Detail detail){
        ResDetailDto resDetailDto=new ResDetailDto();

        resDetailDto.setProductName(detail.getProduct().getNameUz());
        try {
            resDetailDto.setPhoto(detail.getProduct().getPhoto().getBytes());
        }catch (Exception e){
            log.warn("Photo Not Found");
        }
        resDetailDto.setQuantity((short) detail.getQuantity());
        resDetailDto.setPrice(detail.getProduct().getPrice()*detail.getQuantity());

        return resDetailDto;
    }

    public ApiResponse getOne(Long id) {
        Optional<Detail> optionalDetail = detailRepository.findById(id);
        if (optionalDetail.isEmpty()) {
            return ApiResponse.builder().success(false).message("Detail Not Found").build();
        }
        Detail detail = optionalDetail.get();

        return ApiResponse.builder().message("There").success(true).data(detailToRes(detail)).build();
    }

    public ApiResponse edit(Long id, DetailDto detailDto) {
        Optional<Detail> optionalDetail = detailRepository.findById(id);

        if (optionalDetail.isEmpty()) {
            return ApiResponse.builder().success(false).message("Detail Not Found").build();
        }

        Detail detail = optionalDetail.get();

        detail.setQuantity(detailDto.getQuantity());

        Detail save = detailRepository.save(detail);

        return ApiResponse.builder().message("Edited").success(true).data(detailToRes(save)).build();
    }

    public ApiResponse getAll() {
        Pageable pageable= PageRequest.of(0,10);
        Page<Detail> data=null;

       data = detailRepository.findAll(pageable);

       return ApiResponse.builder().message("There").success(true).data(toDTOPage(data)).build();
    }

    public Page<ResDetailDto> toDTOPage(Page<Detail> details) {
        List<ResDetailDto> collect =details.stream().map(this::detailToRes).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    public ApiResponse delete(Long id) {
        Optional<Detail> optionalDetail = detailRepository.findById(id);

        if (optionalDetail.isEmpty()) return ApiResponse.builder().success(false).message("Detail Not Found").build();

        detailRepository.delete(optionalDetail.get());

        return ApiResponse.builder().success(true).message("Deleted").build();
    }
}
