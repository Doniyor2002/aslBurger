package com.example.botservice.config;

import com.example.botservice.entity.Category;
import com.example.botservice.entity.Filial;
import com.example.botservice.repository.CategoryRepository;
import com.example.botservice.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    String name;
    private final CategoryRepository categoryRepository;
    private final FilialRepository filialRepository;

    @Override
    public void run(String... args) throws Exception {
        if (name.equals("always")){
            Filial filial=new Filial();
            filial.setNameUz("gg");
            filial.setNameRu("ll");
            filial.setStatus(true);
            List<Filial> list=new ArrayList<>();
            Filial save = filialRepository.save(filial);
            Category category=new Category();
            Category category1=new Category();
            Category category3=new Category();
            category.setFilial(list);
            category.setNameUz("lavash");
            category.setNameRu("lavash");
            category.setStatus(true);
            category3.setFilial(list);
            category3.setNameUz("Ichimliklar");
            category3.setNameRu("Ichimliklar");
            category3.setStatus(true);
            list.add(save);
            category1.setFilial(list);
            category1.setNameUz("Shaurma");
            category1.setNameRu("Shaurma");
            category1.setStatus(true);
            Category save1 = categoryRepository.save(category);
            Category save2 = categoryRepository.save(category3);
            category1.setParent(save1);
            categoryRepository.save(category1);
        }

    }
}
