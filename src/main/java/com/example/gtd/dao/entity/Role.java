package com.example.gtd.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "gtd_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "rolename", nullable = false)
    private String rolename;


//    @ManyToMany(mappedBy = "roles")
//    @Column(name = "user_id")
//    private Set<User> users;
}
