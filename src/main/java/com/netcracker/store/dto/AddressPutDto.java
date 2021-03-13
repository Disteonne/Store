package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressPutDto {

    @NotNull
    private Long id;
    @NotNull
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String country;
    @NotNull
    //@Pattern(regexp = "^[A-ZА-Я][a-zа-я-\\s]+")
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String city;
    @NotNull
    private String street;
    private String building;
}
