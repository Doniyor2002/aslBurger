package com.example.userservice.component;

import com.example.userservice.entity.Category;
import com.example.userservice.entity.Product;
import com.example.userservice.entity.Role;
import com.example.userservice.entity.User;
import com.example.userservice.repository.CategoryRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")){
            Role role=new Role();
            role.setNameUz("USER");
            role.setDeleted(false);
            role.setStatus(true);
            Role role1 = roleRepository.save(role);
            Category category=new Category();
            category.setNameUz("Lavash");
            category.setDeleted(false);
            category.setStatus(true);
            Category category1 = categoryRepository.save(category);
            Product product=new Product();
            product.setCategory(category1);
            product.setDeleted(false);
            product.setStatus(true);
            product.setPrice(26000D);
            product.setNameUz("Sirli Lavash");
            productRepository.save(product);
            User user=new User();
            user.setPhone("+998900108665");
            user.setFullName("Doniyor Nomozov");
            user.setRole(role1);
            userRepository.save(user);
        }
    }
}
