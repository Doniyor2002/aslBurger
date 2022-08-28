package com.example.userservice.entity;


import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.OrderType;
import com.example.userservice.entity.enums.PayType;
import com.example.userservice.entity.templete.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update orders set deleted=true,status=false where id=?")
public class Order extends AbsEntity {
    //kimga tegishli ekani qo'shish kk
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Detail> detailList;

    //summa
    // Sale ni ham tekshirib kurish k.k
    private Double summa;
    @Enumerated(EnumType.STRING)
    private PayType payType;

    //olib ketish turi
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @OneToOne
    private Address address;

    @ManyToOne
    private Filial filial;

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderStatus=" + orderStatus +
                ", detailList=" + detailList +
                ", summa=" + summa +
                ", payType=" + payType +
                ", orderType=" + orderType +
                ", address=" + address +
                ", filial=" + filial +super.toString()+
                '}';
    }
}
