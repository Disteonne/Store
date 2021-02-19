package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressPutDto {

    @NotNull
    private Long id;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    private String building;
}
