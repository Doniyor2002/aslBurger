package com.example.userservice.entity;

import com.example.userservice.entity.templete.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Favourite extends AbsEntity {
    @OneToOne
    private User user;
    @OneToMany
    private List<Product> productList;

    @Override
    public String toString() {
        return "Favourite{" +
                "user=" + user +
                ", productList=" + productList +super.toString()+
                '}';
    }
}
