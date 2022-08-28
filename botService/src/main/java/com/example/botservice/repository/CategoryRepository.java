package com.example.botservice.repository;

import com.example.botservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByParent_Id(Long id);

}
