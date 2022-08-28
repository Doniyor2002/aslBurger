package com.example.userservice.entity;


import com.example.userservice.entity.templete.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update discount set deleted=true,status=false where id=?")
public class Discount extends AbsNameEntity {
    private Double percentage;
    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private List<Product> product;

    @Override
    public String toString() {
        return "Discount{" +
                "percentage=" + percentage +
                ", product=" + product +super.toString()+
                '}';
    }
}
