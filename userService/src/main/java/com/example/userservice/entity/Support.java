package com.example.userservice.entity;


import com.example.userservice.entity.enums.SupportType;
import com.example.userservice.entity.templete.AbsNameEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@ToString

public class Support extends AbsNameEntity {
    @Enumerated(EnumType.STRING)
    private SupportType supportType;

    @ElementCollection
    private Set<String> phoneList;

    private String title;
    private String description;
    @OneToOne
    private Attachment attachment;

    @Override
    public String toString() {
        return "Support{" +
                "supportType=" + supportType +
                ", phoneList=" + phoneList +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attachment=" + attachment +super.toString()+
                '}';
    }
}
