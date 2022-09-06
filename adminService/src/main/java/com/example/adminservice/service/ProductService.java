package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.dto.ResNotification;
import com.example.adminservice.dto.ResProductDto;
import com.example.adminservice.entity.*;
import com.example.adminservice.exception.ResourceNotFoundException;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.DiscountRepository;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final AttachmentRepository attachmentRepository;

    public ApiResponse add(ProductDto productDto) {
        String saved = "Saved!"; //biror narsa qo`shilmasa Xato bermasdan nima kam ekanini chiqarish uchun
        Product product = new Product();
        product.setNameUz(productDto.getNameUz());
        product.setNameRu(productDto.getNameRu());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if (!categoryOptional.isEmpty()) {
            product.setCategory(categoryOptional.get());
        }
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
        if (!attachmentOptional.isEmpty()) {
            product.setPhoto(attachmentOptional.get());
        } else {
            saved += " Product uchun Rasm tanlanmadi!";
        }
        boolean d = true;
        Optional<Discount> discountOptional = discountRepository.findById(productDto.getDiscountId());
        if (!discountOptional.isEmpty()) {
            product.setDiscount(discountOptional.get());
        } else {
            d = false;
            saved += " Chegirma Yo`q!";
        }
        Product save = productRepository.save(product);

        if (save!=null){
            return ApiResponse.builder().success(true).message(saved).data(toDto(save)).build();
        }
        return ApiResponse.builder().success(false).message("Saqlashda xatolik yuz berdi").build();
    }


    public ApiResponse getAll(int page,int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Product> all = productRepository.findAll(request);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(toDtoPage(all)).build();
    }

    public ApiResponse getAllByCategoryId(Long id) {
        List<Product> allByCategoryId = productRepository.findAllByCategoryId(id);
        List<ResProductDto> productDtoList=new ArrayList<>();
        for (Product product : allByCategoryId) {
            productDtoList.add(toDto(product));
        }

        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(productDtoList).build();
    }

    public ApiResponse getOne(Long id) {
        log.info("getOne start");
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Product yo`q").build();
        }
        log.info("getOne end");
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(toDto(byId.get())).build();
    }

    public ApiResponse update(Long id, ProductDto productDto) {
        String saved = "Saved!";
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()){
            Product product = optional.get();
            product.setNameUz(productDto.getNameUz());
            product.setNameRu(productDto.getNameRu());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
            if (!categoryOptional.isEmpty()) {
                product.setCategory(categoryOptional.get());
            } else {

            }
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
            if (!attachmentOptional.isEmpty()) {
                product.setPhoto(attachmentOptional.get());
            } else {
                saved += " Product uchun Rasm noto`g`ri tanlandi!";
            }
            boolean d = true;
            Optional<Discount> discountOptional = discountRepository.findById(productDto.getDiscountId());
            if (!discountOptional.isEmpty()) {
                product.setDiscount(discountOptional.get());
            } else {
                d = false;
                saved += " Bunday Chegirma Yo`q!";
            }
            Product save = productRepository.save(product);

            return ApiResponse.builder().success(true).message(saved).data(toDto(save)).build();
        }
        return ApiResponse.builder().success(false).message("Bunday id lik product mavjud emas").build();
    }

    public ApiResponse delete(Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
        }
        return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi").build();

    }

    public ApiResponse giveDiscountProduct(Long productId, Long discountId) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()){
            Product product = optional.get();
            Discount discount = discountRepository.findById(discountId).orElseThrow(() -> new ResourceNotFoundException("Discount", "id", discountId));
            product.setDiscount(discount);
            Product save = productRepository.save(product);
            if (save!=null){
                return ApiResponse.builder().success(true).message("Added discount to product!").data(toDto(save)).build();
            }
            else return ApiResponse.builder().success(false).message("Saqlashda Xatolik yuz berdi!").build();
        }
        else   return ApiResponse.builder().success(false).message("Bunday id lik Product mavjud emas!").build();
    }

    public ResProductDto toDto(Product product){
        StringBuilder builder=new StringBuilder();
        if (product.getCategory()!=null){
            builder.append(" nameUz: "+product.getCategory().getNameUz())
                    .append(" nameRu: "+product.getCategory().getNameRu());
        }
        return ResProductDto.builder()
                .nameRu(product.getNameUz())
                .nameUz(product.getNameUz())
                .description(product.getDescription())
                .categoryName(String.valueOf(builder))
                .price(product.getPrice())
                .discountPercentage(product.getDiscount().getPercentage())
                .build();
    }

    public Page<ResProductDto> toDtoPage(Page<Product> productPage){
        List<ResProductDto> collect = productPage.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
