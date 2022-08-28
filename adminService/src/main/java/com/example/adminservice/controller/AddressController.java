package com.example.adminservice.controller;

import com.example.adminservice.dto.AddressDto;
import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    //yangi address qo`shish
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody AddressDto addressDto){
        ApiResponse response = addressService.save(addressDto);
        return ResponseEntity.ok().body(response);
    }

    //barcha Addresslarni ko`rish
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = addressService.getAll();
        return ResponseEntity.ok().body(response);
    }

    //1 ta Adressni ko`rish (id si bo`yicha)
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse one = addressService.getOne(id);
        return ResponseEntity.ok().body(one);
    }

    //Addresni o`chirib tashlash (id si bo`yicha)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = addressService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
