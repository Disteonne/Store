package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductBasketDto {
    private Long id;
    private String name;
    private String type;
    private BigDecimal price;
    private int count;
    private String info;
    private Long supplierId;
}
