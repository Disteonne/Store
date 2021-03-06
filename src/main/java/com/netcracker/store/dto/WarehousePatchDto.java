package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WarehousePatchDto {
    //info about product
    private String productOldName;
    private String productNewName;
    private String type;
    private BigDecimal price=BigDecimal.ZERO;
    private int count;
    private String info;

    //info about supplier
    private String supplierName;
    private String mail;

    //info about address of supplier
    private String country;
    private String city;
    private String street;
    private String building;
}
