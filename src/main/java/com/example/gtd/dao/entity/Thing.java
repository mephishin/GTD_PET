package com.example.gtd.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "thing", schema = "thing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thing_id")
    private Long id;

    @Column(name = "thing_name", unique = true, nullable = false)
    private String thingName;

    @ManyToOne()
    private User user;

    @Column(name = "thing_kind", unique = false, nullable = false)
    private String thingKind;

}
