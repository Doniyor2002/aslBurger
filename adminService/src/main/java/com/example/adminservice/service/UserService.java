package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.UserDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.entity.Role;
import com.example.adminservice.entity.User;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.repository.RoleRepository;
import com.example.adminservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final FilialRepository filialRepository;

    public ApiResponse addCourier(UserDto userDto) {
        User user = new User();
        user.setFullName(user.getFullName());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        Optional<Filial> filialOptional = filialRepository.findById(userDto.getFilialId());
        if (!filialOptional.isEmpty()) {
            Filial filial = filialOptional.get();
            user.setFilial(filial);
        }
        User save = userRepository.save(user);
        return ApiResponse.builder().success(true).message("Courier Saved!").data(save).build();
    }

    public ApiResponse getAllEmployees() {
        Optional<Role> roleOptional = roleRepository.findById(2l);
        Role role = roleOptional.get();
        List<User> allByRole = userRepository.findAllByRoleNot(role);
        return ApiResponse.builder().success(true).message("Bori shu \uD83D\uDE43").data(allByRole).build();
    }

    public ApiResponse getFilialByUserId(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.builder().success(false).message("Bunday Id lik User yo`q \uD83D\uDE1C").build();
        }
        User user = byId.get();
        Filial filial = user.getFilial();
        return ApiResponse.builder().success(true).message("Topildi \uD83D\uDE43").data(filial).build();
    }

}
