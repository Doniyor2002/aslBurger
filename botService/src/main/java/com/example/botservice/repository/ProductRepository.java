package com.example.botservice.repository;

import com.example.botservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory_Id(Long id);

    List<Product> findAllByDiscount_Id(Long discountId);
}
