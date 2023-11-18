package com.example.gtd.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@Entity
@Table(name = "thing", schema = "thing")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thing_id")
    private Long id;

    @Column(name = "thing_name", unique = true, nullable = false)
    private String thingName;

    @ManyToMany()
    @Column(name = "user_id")
    @JoinTable(
            name = "thing_user",
            joinColumns = { @JoinColumn(name = "thing_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private Set<User> user;

    @Column(name = "thing_kind", unique = false, nullable = false)
    private String thingKind;

}
