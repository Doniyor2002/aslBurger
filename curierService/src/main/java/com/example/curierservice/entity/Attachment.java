package com.example.curierservice.entity;

import com.example.curierservice.entity.templete.AbsEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Attachment extends AbsEntity {
    private String fileName;
    private String contentType; //.xls .doc
    private long size;
    private String url; //url
    private byte[] bytes;
}
