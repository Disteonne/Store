package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    
    private Long id;
    private String name;
    private String type;
    private BigDecimal price;
    private Integer count;
    private Long supplierId;
    private String info;
}
