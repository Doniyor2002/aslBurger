package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final FilialRepository filialRepository;

    public ApiResponse save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setNameUz(categoryDto.getNameUz());
        category.setNameRu(categoryDto.getNameRu());
        if (categoryDto.getParentId()!=0){
            Optional<Category> byId = categoryRepository.findById(categoryDto.getParentId());
            if (!byId.isEmpty()) {
            Category category1 = byId.get();
            category.setParent(category1);
            }
        }

        //id si berilgan Filial bor bo`lsa categoryga qo`shiladi
        List<Filial> filialList = new ArrayList<>();
        List<Long> filialsId = categoryDto.getFilialsId();
        for (Long aLong : filialsId) {
            Optional<Filial> filialOptional = filialRepository.findById(aLong);
            try {
                Filial filial = filialOptional.get();
                filialList.add(filial);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        category.setFilial(filialList);
        Category save = categoryRepository.save(category);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Category yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(byId.get()).build();
    }

    public ApiResponse update(Long id, CategoryDto categoryDto) {

        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri id kiritildi!").build();
        }
        Category category = byId.get();
        category.setNameUz(categoryDto.getNameUz());
        category.setNameRu(categoryDto.getNameRu());

        if (categoryDto.getParentId()!=0){
            Optional<Category> byId2 = categoryRepository.findById(categoryDto.getParentId());
            if (!byId2.isEmpty()) {
                Category category1 = byId2.get();
                category.setParent(category1);
            }
        }

        //id si berilgan Filial bor bo`lsa categoryga qo`shiladi
        List<Filial> filialList = new ArrayList<>();
        List<Long> filialsId = categoryDto.getFilialsId();
        for (Long aLong : filialsId) {

            Optional<Filial> filialOptional = filialRepository.findById(aLong);
            try {
                Filial filial = filialOptional.get();
                filialList.add(filial);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        category.setFilial(filialList);

        Category save = categoryRepository.save(category);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse delete(Long id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi").build();
        }
        categoryRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
    }

}
