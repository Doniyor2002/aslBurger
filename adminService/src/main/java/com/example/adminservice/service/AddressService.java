package com.example.adminservice.service;

import com.example.adminservice.dto.AddressDto;
import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.entity.Address;
import com.example.adminservice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    //AddressDto keladi
    //Addresni Uz va Ru nomlarini berish uchun AddressDto dan foydaliniladi
    private final AddressRepository addressRepository;
    public ApiResponse save(AddressDto addressDto) {
        Address address = new Address();
        address.setNameUz(addressDto.getNameUz());
        address.setNameRu(addressDto.getNameRu());
        address.setLat(addressDto.getLat());
        address.setLon(addressDto.getLon());
        address.setTarget(addressDto.getTarget());
        Address save = addressRepository.save(address);
        return ApiResponse.builder().success(true).message("Saved!").data(save).build();
    }

    public ApiResponse getAll() {
        List<Address> all = addressRepository.findAll();
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(all).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Address yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDE43").data(byId.get()).build();
    }
    public ApiResponse delete(Long id){
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().build();
        }
        addressRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
    }

}
