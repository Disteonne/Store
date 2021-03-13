package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class WarehousePostDto {

    //info about product
    @NotNull
    @NotEmpty
    private String productName;
    @NotNull
    @NotEmpty
    private String type;
    @NotNull
    @NotEmpty
    private BigDecimal price=BigDecimal.ZERO;
    @Min(1)
    private int count;
    @NotNull
    @NotEmpty
    private String info;

    //info about supplier
    @NotNull
    @NotEmpty
    private String supplierName;
    @NotNull
    @NotEmpty
    private String mail;

    //info about address of supplier
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String street;
    private String building;

}
