package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.DiscountDto;
import com.example.adminservice.entity.Discount;
import com.example.adminservice.entity.Product;
import com.example.adminservice.repository.DiscountRepository;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;
    public ApiResponse save(DiscountDto discountDto) {
        Discount discount = new Discount();
        discount.setNameUz(discountDto.getNameUz());
        discount.setNameRu(discountDto.getNameRu());
        discount.setPercentage(discountDto.getPercentage());

        List<Product> allById = new ArrayList<>();
        for (Long aLong : discountDto.getProductsId()) {

            Optional<Product> productOptional = productRepository.findById(aLong);
            try {
                Product product = productOptional.get();
                allById.add(product);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        discount.setProduct(allById);
        Discount save = discountRepository.save(discount);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse getAll() {
        List<Discount> all = discountRepository.findAll();
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Discount> byId = discountRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Discount yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDE43").data(byId.get()).build();
    }

    public ApiResponse update(Long id, DiscountDto discountDto) {
        Optional<Discount> byId = discountRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri id kiritildi!").build();
        }

        Discount discount = byId.get();
        discount.setNameUz(discountDto.getNameUz());
        discount.setNameRu(discountDto.getNameRu());
        discount.setPercentage(discountDto.getPercentage());

        List<Product> allById = new ArrayList<>();
        for (Long aLong : discountDto.getProductsId()) {

            Optional<Product> productOptional = productRepository.findById(aLong);
            try {
                Product product = productOptional.get();
                allById.add(product);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        discount.setProduct(allById);

        Discount save = discountRepository.save(discount);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }
    public ApiResponse delete(Long id){
        Optional<Discount> byId = discountRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().build();
        }
        discountRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
    }
}
