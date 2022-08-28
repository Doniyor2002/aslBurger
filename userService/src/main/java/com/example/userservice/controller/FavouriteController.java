package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.FavouriteDto;
import com.example.userservice.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<?> addFavourite(@Valid @RequestBody FavouriteDto favouriteDto){
        ApiResponse response=favouriteService.add(favouriteDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavourite(@PathVariable Long id){
        ApiResponse response=favouriteService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneFavourite(@PathVariable Long id){
        ApiResponse response=favouriteService.getOne(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAllFavourite(@RequestParam(required = false) Long userId){
        ApiResponse response=favouriteService.getAll(userId);
        return ResponseEntity.ok(response);
    }
}
