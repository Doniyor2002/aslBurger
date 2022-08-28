package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Discount;
import com.example.adminservice.entity.Product;
import com.example.adminservice.repository.AttachmentRepository;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.DiscountRepository;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

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

        //Discountga productni qo`shib qo`yish
        if (d) {
            Discount discount = discountOptional.get();
            List<Product> product1 = discount.getProduct();
            product1.add(save);
            discount.setProduct(product1);
            discountRepository.save(discount);
        }

        return ApiResponse.builder().success(true).message(saved).data(save).build();
    }


    public ApiResponse getAll() {
        List<Product> all = productRepository.findAll();
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }

    public ApiResponse getAllByCategoryId(Long id) {
        List<Product> allByCategoryId = productRepository.findAllByCategoryId(id);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(allByCategoryId).build();
    }

    public ApiResponse getOne(Long id) {
        log.info("getOne start");
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Product yo`q").build();
        }
        log.info("getOne end");
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(byId.get()).build();
    }

    public ApiResponse update(Long id, ProductDto productDto) {
        String saved = "Saved!";

        Product product = new Product();
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

        //Discountga productni qo`shib qo`yish
        if (d) {
            Discount discount = discountOptional.get();
            List<Product> product1 = discount.getProduct();
            product1.add(save);
            discount.setProduct(product1);
            discountRepository.save(discount);
        }
        return ApiResponse.builder().success(true).message(saved).data(save).build();
    }

    public ApiResponse delete(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi").build();
        }
        productRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
    }
}
