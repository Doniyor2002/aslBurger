package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.UserDto;
import com.example.adminservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/courier")
    public ResponseEntity<?> addCourier(@Valid @RequestBody UserDto userDto){
        ApiResponse response = userService.addCourier(userDto);
        return ResponseEntity.ok().body(response);
    }

    //tekshirish kerak
    @GetMapping("/employee")
    public ResponseEntity<?> getAllEmployees(){
        ApiResponse response = userService.getAllEmployees();
        return ResponseEntity.ok().body(response);
    }

    //User(Ishchi) qaysi Filialda ishlashi
    @GetMapping("/filial/{id}")
    public ResponseEntity<?> getFilialByUserId(@PathVariable Long id){
        ApiResponse one = userService.getFilialByUserId(id);
        return ResponseEntity.ok().body(one);
    }
}
