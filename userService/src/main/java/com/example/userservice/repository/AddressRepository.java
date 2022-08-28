package com.example.userservice.repository;

import com.example.userservice.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Page<Address> findAllByUser_Id(Long userId, Pageable pageable);
}
