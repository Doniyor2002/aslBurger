package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.DiscountDto;
import com.example.adminservice.dto.ResCategoryDto;
import com.example.adminservice.dto.ResDiscountDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Discount;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.entity.Product;
import com.example.adminservice.repository.DiscountRepository;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (save!=null){
            return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
        }
        return ApiResponse.builder().success(false).message("Xatolik yuz berdi!").build();
    }

    public ApiResponse getAll(int page,int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Discount> all = discountRepository.findAll(pageRequest);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(toDtoPage(all)).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Discount> byId = discountRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Discount yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDE43").data(toDto(byId.get())).build();
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
        return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
    }
    public ApiResponse delete(Long id){
        boolean exists = discountRepository.existsById(id);
        if (exists) {
            discountRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
        }
        return ApiResponse.builder().success(false).message("Xatolik yuz berdi").build();
    }

    public ResDiscountDto toDto(Discount discount){
        List<String> productList=new ArrayList<>();
        for (Product product : discount.getProduct()) {
            StringBuilder builder=new StringBuilder();
            builder.append(" nameUz: "+product.getNameUz())
                    .append("   nameRu: "+product.getNameRu());
            productList.add(String.valueOf(builder));
        }
        return ResDiscountDto.builder()
                .nameRu(discount.getNameUz())
                .nameUz(discount.getNameUz())
                .percentage(discount.getPercentage())
                .productsName(productList)
                .build();
    }

    public Page<ResDiscountDto> toDtoPage(Page<Discount> discountPage){
        List<ResDiscountDto> collect = discountPage.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
