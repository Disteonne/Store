package com.netcracker.store.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductDto {


    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private BigDecimal price=new BigDecimal(0);
    @Min(1)
    private Integer count;
    @NotNull
    private Long supplierId;
    @NotNull
    private String info;
}
