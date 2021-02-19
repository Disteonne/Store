package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductPostDto {

    @NotNull
    private String name;
    private String type;
    @NotNull
    private BigDecimal price;
    private int count;
    @NotNull
    private Long supplierId;
    @NotNull
    private String info;
}
