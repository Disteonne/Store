package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {

    private Long id;
    private String name;
    private String mail;
    private Long addressId;
}
