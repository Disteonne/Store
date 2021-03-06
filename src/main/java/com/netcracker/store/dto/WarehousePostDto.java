package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WarehousePostDto {

    //info about product
    private String productName; //NOT NULL
    private String type;    //NOT NULL
    private BigDecimal price=BigDecimal.ZERO;   //NOT NULL
    private int count;  //NOT NULL
    private String info;    //NOT NULL

    //info about supplier
    private String supplierName;    //NOT NULL
    private String mail;    //NOT NULL

    //info about address of supplier
    private String country; //NOT NULL
    private String city;    //NOT NULL
    private String street;  //NOT NULL
    private String building;    //MAY BE NULL

}
