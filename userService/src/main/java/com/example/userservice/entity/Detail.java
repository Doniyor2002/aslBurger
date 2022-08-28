package com.example.userservice.entity;


import com.example.userservice.entity.templete.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update detail set deleted=true,status=false where id=?")
public class Detail extends AbsNameEntity {
    //kimga tegishli ekanligi
    @ManyToOne
    private User user;

    @ManyToOne()
    @JoinColumn(name = "ord_id")
    private Order order;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Override
    public String toString() {
        return "Detail{" +
                "user=" + user +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +super.toString()+
                '}';
    }
}
