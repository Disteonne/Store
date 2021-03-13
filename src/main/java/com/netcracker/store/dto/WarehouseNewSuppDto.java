package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class WarehouseNewSuppDto {
    //info about product
    private String productOldName;
    private String productNewName;
    private String type;
    private BigDecimal price=BigDecimal.ZERO;
    private int count;
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
