package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.dto.ResCategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.FilialRepository;
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
        return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
    }

    public ApiResponse getAll(int page,int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> all = categoryRepository.findAll(pageRequest);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(toDtoPage(all)).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Category yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(toDto(byId.get())).build();
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
        return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
    }

    public ApiResponse delete(Long id){
        boolean exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();

        }
        return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi").build();
    }
    public ResCategoryDto toDto(Category category){
        StringBuilder builder=new StringBuilder();
        if (category.getParent()!=null){
            builder.append(" nameUz: "+category.getParent().getNameUz())
                    .append("   nameRu: "+category.getParent().getNameRu());
        }
        List<String> filialList=new ArrayList<>();
        for (Filial filial : category.getFilial()) {
            StringBuilder filialName=new StringBuilder();
            filialName.append(" nameUz: "+filial.getNameUz())
                    .append("   nameRu: "+filial.getNameRu());
            filialList.add(String.valueOf(filialName));
        }
        return ResCategoryDto.builder().nameRu(category.getNameRu())
                .nameUz(category.getNameUz())
                .parentName(String.valueOf(builder))
                .filialName(filialList)
                .build();
    }
    public Page<ResCategoryDto> toDtoPage(Page<Category> categoryPage){
        List<ResCategoryDto> collect = categoryPage.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

}
