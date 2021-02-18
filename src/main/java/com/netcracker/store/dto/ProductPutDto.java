package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductPutDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
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
