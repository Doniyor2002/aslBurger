package com.example.adminservice.entity;


import com.example.adminservice.entity.templete.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update sale set deleted=true,status=false where id=?")
public class Sale extends AbsNameEntity {
    private Timestamp start;
    private Timestamp ending;
    private Double summa;
    private Double foiz;

    @Override
    public String toString() {
        return "Sale{" +
                "start=" + start +
                ", ending=" + ending +
                ", summa=" + summa +
                ", foiz=" + foiz +super.toString()+
                '}';
    }
}