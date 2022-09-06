package com.example.operatorservice.entity;

import com.example.operatorservice.entity.templete.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update users set deleted=true,status=false where id=?")
public class User extends AbsNameEntity {
    @Column(unique = true)
    private String phone;
    private String password;
    private String fullName;
    private int reliabilty=0;//Ishonchliligi
    private Long chatId;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> messageList;

    private Boolean online = null;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addressList;
    @ManyToOne()
    private Filial filial;
    private String region;
    @ManyToOne()
    private Role role;


    public User(String phone, Long chatId) {
        this.phone = phone;
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", reliabilty=" + reliabilty +
                ", chatId=" + chatId +
                ", messageList=" + messageList +
                ", online=" + online +
                ", filial=" + filial +
                ", region='" + region + '\'' +
                ", enabled=" + enabled +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", credentialsExpired=" + credentialsExpired +
                ", role=" + role +super.toString()+
                '}';
    }
}
