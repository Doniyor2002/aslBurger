package com.example.userservice.controller;

import com.example.userservice.entity.Address;
import com.example.userservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("address")
@RestController
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<?> getUserAddress(@RequestParam Long userId){
        Pageable pageable= PageRequest.of(0,10);
        Page<Address> data= null;

        data = addressRepository.findAllByUser_Id(userId,pageable);

        return ResponseEntity.ok(data);
    }
}
