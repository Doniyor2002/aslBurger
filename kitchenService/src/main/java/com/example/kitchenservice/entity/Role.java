package com.example.kitchenservice.entity;

import com.example.kitchenservice.entity.enums.Permission;
import com.example.kitchenservice.entity.templete.AbsNameEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.List;


@Entity(name = "roles")
@NoArgsConstructor
@Getter
@Setter
@ToString
@Where(clause = "deleted=false")
@SQLDelete(sql = "update role set deleted=true,status=false where id=?")
public class Role extends AbsNameEntity {
    @ElementCollection()
    @Enumerated
    private List<Permission> permissionList;


    public Role(Long id, String nameUz, String nameRu, boolean deleted, boolean status, List<Permission> permissionList) {
        super(id, nameUz, nameRu, deleted, status);
        this.permissionList = permissionList;
    }




    @Override
    public String toString() {
        return "Role{" +
                "permissionList=" + permissionList +super.toString()+
                '}';
    }
}
