package com.example.adminservice.entity;

import com.example.adminservice.entity.templete.AbsNameEntity;
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
    private boolean enabled = true;
    private boolean accountExpired=true;
    private boolean accountLocked=true;
    private boolean credentialsExpired=true;
    @ManyToOne()
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        return this.role.getPermissionList().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.phone;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.accountLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }

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
