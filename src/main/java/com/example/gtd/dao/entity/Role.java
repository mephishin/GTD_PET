package com.example.gtd.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "gtd_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "rolename", nullable = false)
    private String rolename;

//    После того как убрал это в гет методах появилась инфа о ролях
//    @ManyToMany(mappedBy = "roles")
//    @Column(name = "user_id")
//    private Set<User> users;

    public Role(String rolename) {
        this.rolename = rolename;
    }
}
