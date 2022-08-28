package com.example.kitchenservice.entity;

import com.example.kitchenservice.entity.templete.AbsNameEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update notification set deleted=true,status=false where id=?")
public class Notification  extends AbsNameEntity {
    @ManyToOne
    private User user;
    private String title;
    private String body;
    @OneToOne
    private Attachment attachment; //attachment
    private boolean hasBot; //true
    private Timestamp sendTime;

    @Override
    public String toString() {
        return "Notification{" +
                "user=" + user +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", attachment=" + attachment +
                ", hasBot=" + hasBot +
                ", sendTime=" + sendTime +super.toString()+
                '}';
    }
}
