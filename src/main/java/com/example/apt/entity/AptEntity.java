package com.example.apt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="apt")
public class AptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private int yprice;

    @Column
    private int dprice;

    private int ymin;

    private int ymax;

    private int dmin;

    private int dmax;



}
