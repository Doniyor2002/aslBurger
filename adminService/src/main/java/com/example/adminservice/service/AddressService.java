package com.example.adminservice.service;

import com.example.adminservice.dto.AddressDto;
import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ResProductDto;
import com.example.adminservice.entity.Address;
import com.example.adminservice.entity.Product;
import com.example.adminservice.repository.AddressRepository;
import com.example.adminservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    public ApiResponse save(AddressDto addressDto) {
        Address address = new Address();
        address.setNameUz(addressDto.getNameUz());
        address.setNameRu(addressDto.getNameRu());
        address.setLat(addressDto.getLat());
        address.setLon(addressDto.getLon());
        address.setTarget(addressDto.getTarget());
        Address save = addressRepository.save(address);
        if (save!=null){
            return ApiResponse.builder().success(true).message("Saved!").data(toDto(save)).build();
        }
        return ApiResponse.builder().success(false).message("Xatolik yuz berdi!").data(save).build();
    }

    public ApiResponse getAll(int page,int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Address> addresses = addressRepository.findAll(pageRequest);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(toDtoPage(addresses)).build();
    }
    public ApiResponse getOne(Long id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik Address yo`q").build();
        }
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDE43").data(toDto(byId.get())).build();
    }
    public ApiResponse delete(Long id){
        boolean exists = addressRepository.existsById(id);
        if (exists) {
            addressRepository.deleteById(id);
            return ApiResponse.builder().success(true).message("O`chirvordim \uD83D\uDEAE").build();
        }
        return ApiResponse.builder().success(false).message("Bunday Id lik Address yo`q").build();
    }


    public AddressDto toDto(Address address){
        return AddressDto.builder().nameRu(address.getNameRu())
                .nameUz(address.getNameUz())
                .lat(address.getLat())
                .lon(address.getLon())
                .target(address.getTarget()).build();
    }
    public Page<AddressDto> toDtoPage(Page<Address> address){
        List<AddressDto> collect = address.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

}
