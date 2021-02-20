package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductPostDto {

    @NotNull
    private String name;
    private String type;
    @NotNull
    private BigDecimal price;
    @Min(1)
    private int count;
    @NotNull
    private Long supplierId;
    @NotNull
    private String info;
}
