package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.FavouriteDto;
import com.example.userservice.dto.ResFavouriteDto;
import com.example.userservice.entity.Favourite;
import com.example.userservice.entity.Product;
import com.example.userservice.repository.FavouriteRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FavouriteService {
    @Autowired
    FavouriteRepository favouriteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse add(FavouriteDto favouriteDto) {
        Favourite favourite=new Favourite();
        favourite.setDeleted(false);
        favourite.setStatus(true);
        favourite.setUser(userRepository.findById(favouriteDto.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));
        favourite.setProductList(productRepository.findAllById(favouriteDto.getProductIds()));
        favouriteRepository.save(favourite);
        return ApiResponse.builder().message("Added").success(true).build();
    }
    public ApiResponse delete(Long id) {
        try {
            Favourite favourite = favouriteRepository.findById(id).get();
            favouriteRepository.delete(favourite);
            return ApiResponse.builder().success(true).message("Deleted").build();
        }catch (Exception e){
            log.error("error -> "+e);
        }
       return ApiResponse.builder().message("Error").success(false).build();
    }
    public ApiResponse getOne(Long id) {
        Favourite favourite = favouriteRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return ApiResponse.builder().success(true).message("There").data(favouriteToDto(favourite)).build();
    }
    public ResFavouriteDto favouriteToDto(Favourite favourite){
        ResFavouriteDto resFavouriteDto=new ResFavouriteDto();
        resFavouriteDto.setUserName(favourite.getUser().getFullName());
        List<String> productNames=new ArrayList<>();
        for (Product product : favourite.getProductList()) {
            productNames.add(product.getNameUz());
        }
        resFavouriteDto.setProductNames(productNames);
        return resFavouriteDto;
    }
    public ApiResponse getAll(Long userId) {
        Pageable pageable= PageRequest.of(0,10);
        Page<Favourite> data=null;
        if (String.valueOf(userId) != "null") data=favouriteRepository.findAllByUser_Id(userId,pageable);
        else data=favouriteRepository.findAll(pageable);
        return ApiResponse.builder().message("There").success(true).data(toDTOPage(data)).build();
    }
    public Page<ResFavouriteDto> toDTOPage(Page<Favourite> favourites) {
        List<ResFavouriteDto> collect =favourites.stream().map(this::favouriteToDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
