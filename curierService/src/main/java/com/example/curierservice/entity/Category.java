package com.example.curierservice.entity;


import com.example.curierservice.entity.templete.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update category set deleted=true,status=false where id=?")
public class Category extends AbsNameEntity {
    @ManyToOne
    private Category parent;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Filial> filial;

    @Override
    public String toString() {
        return "Category{" +
                "parent=" + parent +
                ", filial=" + filial +super.toString()+
                '}';
    }
}
