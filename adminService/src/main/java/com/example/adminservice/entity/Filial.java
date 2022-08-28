package com.example.adminservice.entity;

import com.example.adminservice.entity.templete.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
//@Where(clause = "delete = false")
//@SQLDelete(sql = "update filial set deleted = true,status = false where id=?")
public class Filial extends AbsNameEntity {

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @OneToOne()
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "filial")
    private List<User> user;

    @Override
    public String toString() {
        return "Filial{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", address=" + address +
                ", user=" + user +super.toString()+
                '}';
    }
}
