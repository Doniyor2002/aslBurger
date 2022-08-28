package com.example.kitchenservice.entity;


import com.example.kitchenservice.entity.templete.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
public class Address extends AbsNameEntity {
    @ManyToOne
    private User user;
    private Double lat;
    private Double lon;
    private String target;

    @Override
    public String toString() {
        return "Address{" +
                "user=" + user +
                ", lat=" + lat +
                ", lon=" + lon +
                ", target='" + target + '\'' +super.toString()+
                '}';
    }
}
