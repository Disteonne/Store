package com.netcracker.store.entity;

import lombok.Data;


import javax.persistence.*;
import java.util.List;


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

    @OneToMany(mappedBy = "id")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Address address;
}
