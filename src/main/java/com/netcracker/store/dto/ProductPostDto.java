package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

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
