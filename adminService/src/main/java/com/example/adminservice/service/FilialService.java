package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Address;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.AddressRepository;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialService {
    private final FilialRepository filialRepository;
    private final AddressRepository addressRepository;
    private final DateFormatUtil dateFormatUtil;

    public ApiResponse save(FilialDto filialDto) {
        Filial filial = new Filial();
        filial.setNameUz(filialDto.getNameUz());
        filial.setNameRu(filialDto.getNameRu());

        //FilialDto da startTime va endTime HH:mm ko`rinishda yoziladi
        Date startTime = dateFormatUtil.dateConvertorTime(filialDto.getStartTime());
        Date endTime = dateFormatUtil.dateConvertorTime(filialDto.getEndTime());
        filial.setStartTime(startTime);
        filial.setEndTime(endTime);

        //id si berilgan Address bor bo`lsa filialga qo`shiladi
        Optional<Address> addressOptional = addressRepository.findById(filialDto.getAddressId());
        try {
            Address address = addressOptional.get();
            filial.setAddress(address);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        Filial save = filialRepository.save(filial);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse getAll(int page,int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Filial> all = filialRepository.findAll(request);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Filial yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDC4C").data(byId.get()).build();
    }

    public ApiResponse update(Long id, FilialDto filialDto) {

        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Noto`g`ri id kiritildi!").build();
        }
        Filial filial = byId.get();
        filial.setNameUz(filialDto.getNameUz());
        filial.setNameRu(filialDto.getNameRu());

        //FilialDto da startTime va endTime HH:mm ko`rinishda yoziladi
        Date startTime = dateFormatUtil.dateConvertorTime(filialDto.getStartTime());
        Date endTime = dateFormatUtil.dateConvertorTime(filialDto.getEndTime());

        filial.setStartTime(startTime);
        filial.setEndTime(endTime);

        //id si berilgan Address bor bo`lsa filialga qo`shiladi
        Optional<Address> addressOptional = addressRepository.findById(filialDto.getAddressId());
        try {
            Address address = addressOptional.get();
            filial.setAddress(address);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        Filial save = filialRepository.save(filial);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse delete(Long id){
        boolean exists = filialRepository.existsById(id);
        if (exists) {
            filialRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
        }
        return ApiResponse.builder().success(false).message("Noto`g`ri Id kiritildi").build();

    }
}
