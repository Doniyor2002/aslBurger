package com.example.userservice.controller;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserDto userDto){
        ApiResponse response=userService.signUp(userDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        ApiResponse response=userService.delete(id);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id,@Valid @RequestBody(required = false) UserDto userDto,@RequestParam(required = false) Boolean online){
        ApiResponse response=userService.edit(id,userDto,online);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }//done
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        ApiResponse response=userService.getOne(id);
        return ResponseEntity.ok(response);
    }//done
    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "null",required = false) String role,
                                         @RequestParam(defaultValue = "null",required = false)Boolean online){
        ApiResponse response=userService.getAll(role,online);
        return ResponseEntity.ok(response);
    }//??? bad request required iwlamayapt
}
