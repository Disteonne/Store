package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierGetDto {
    private Long id;
    private String name;
    private String mail;
    private String country;
    private String city;
    private String street;
    private String building;
}
