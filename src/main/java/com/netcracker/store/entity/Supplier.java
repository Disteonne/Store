package com.netcracker.store.entity;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    private long addressId;
}
