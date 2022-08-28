package com.example.userservice.service;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.dto.ResUserDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public ApiResponse signUp(UserDto userDto) {
        if (userDto.isAgreement()) {
            if (userDto.getPassword().equals(userDto.getSecondPassword())) {
                User user=new User();

                user.setReliabilty(0);
                user.setRegion(userDto.getRegion());
                user.setPhone(userDto.getPhone());
                user.setFullName(userDto.getFullName());
                user.setAccountExpired(false);
                user.setAccountLocked(false);
                user.setEnabled(true);
                user.setPassword(userDto.getPassword());
                user.setCredentialsExpired(false);
                user.setRole(roleRepository.findByNameUzContainingIgnoreCase("USER").orElseThrow(() -> new RuntimeException("Role Not Found")));
                user.setStatus(true);
                User save = userRepository.save(user);
                return ApiResponse.builder().success(true).message("Registration successful").build();
            }
        }
        return ApiResponse.builder().success(false).message("Fail").build();
    }

    public ApiResponse delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ApiResponse.builder().success(false).message("User Not Found").build();
        }
        User user = optionalUser.get();

        userRepository.delete(user);

        return ApiResponse.builder().message("Deleted").success(true).build();
    }
    public ApiResponse edit(Long id, UserDto userDto,Boolean online) {
        if (!(online.equals(null))){
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isEmpty()) return ApiResponse.builder().success(false).message("User Not Found").build();

            User user = optionalUser.get();
            user.setOnline(online);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) return ApiResponse.builder().success(false).message("User Not Found").build();

        User user = optionalUser.get();

        user.setRegion(userDto.getRegion());
        user.setPhone(userDto.getPhone());
        user.setFullName(userDto.getFullName());
        userRepository.save(user);
        return ApiResponse.builder().success(true).message("User updated").build();
    }

    public ApiResponse getOne(Long id) {
        User user=new User();
        try {
            user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        }catch (Exception e){
            log.error(String.valueOf(e));
            return ApiResponse.builder().success(false).build();
        }
        return ApiResponse.builder().success(true).message("There").data(userToDto(user)).build();
    }
    public ResUserDto userToDto(User user) {
        ResUserDto resUserDto = new ResUserDto();

        resUserDto.setPhone(user.getPhone());
        resUserDto.setRegion(user.getRegion());
        resUserDto.setReliability(user.getReliabilty());
        resUserDto.setFullName(user.getFullName());
        try {
            resUserDto.setFilialName(user.getFilial().getNameUz());
        }catch (Exception e){
            log.warn("FilialName Not Found");
        }
        resUserDto.setRoleName(user.getRole().getNameUz());

        return resUserDto;
    }

    public ApiResponse getAll(String role,Boolean online) {
        Pageable pageable= PageRequest.of(0,10);
        Page<User> data= null;
        if ((role.equals(null))&&(String.valueOf(online).equals("null"))){
          data = userRepository.findAll(pageable);//for Admin
        }
        data = userRepository.findAllByRole_NameContainingIgnoreCaseAndOnline(role,online,pageable);//for operator

        return ApiResponse.builder().success(true).message("There").data(toDTOPage(data)).build();
    }
    public Page<ResUserDto> toDTOPage(Page<User> users) {
        List<ResUserDto> collect =users.stream().map(this::userToDto).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}
