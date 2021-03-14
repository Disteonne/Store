package com.netcracker.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;    //можно так же создать таблицу отдельно

    @Column(name = "price")
    private BigDecimal price=BigDecimal.ZERO;

    @Column(name = "count")
    private Integer count;

    @ManyToOne()//cascade = CascadeType.ALL
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "info")
    private String info;
}
