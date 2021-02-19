package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SupplierPostDto {

    @NotNull
    private String name;
    @NotNull
    private String mail;
    @NotNull
    private Long addressId;
}
