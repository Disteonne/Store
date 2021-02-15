package com.netcracker.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_product")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "count")
    private int count;


    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "info")
    private String info;
}
