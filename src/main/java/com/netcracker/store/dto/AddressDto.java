package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String building;
}
