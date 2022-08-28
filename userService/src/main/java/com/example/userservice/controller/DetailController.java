package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.DetailDto;
import com.example.userservice.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    DetailService detailService;

    @PostMapping
    public ResponseEntity<?> addDetail(@Valid @RequestBody DetailDto detailDto){
        ApiResponse response=detailService.add(detailDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneDetail(@PathVariable Long id){
        ApiResponse response=detailService.getOne(id);
        return ResponseEntity.ok(response);
    }//done

    @PutMapping("/{id}")
    public ResponseEntity<?> editDetail(@PathVariable Long id,@Valid @RequestBody DetailDto detailDto){
        ApiResponse response=detailService.edit(id,detailDto);

        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done

    @GetMapping
    public ResponseEntity<?> getAllDetail(){
        ApiResponse response=detailService.getAll();
        return ResponseEntity.ok(response);
    }//done

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetail(@PathVariable Long id){
        ApiResponse response=detailService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done
}
