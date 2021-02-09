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

    @OneToMany
    @JoinColumn(name = "supplier_id")
    private List<Product> product;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Address> address;
}
