package com.example.userservice.entity;


import com.example.userservice.entity.templete.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update product set deleted=true,status=false where id=?")
public class Product extends AbsNameEntity {
    @ManyToOne
    private Category category;
    @OneToOne(fetch = FetchType.EAGER)
    private Attachment photo;
    private Double price;
    private String description;
    //chegirma
    @ManyToOne
    private Discount discount;

    @Override
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", photo=" + photo +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", discount=" + discount +super.toString()+
                '}';
    }
}
