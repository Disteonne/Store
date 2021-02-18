package com.netcracker.store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier")
    @JsonIgnore
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
