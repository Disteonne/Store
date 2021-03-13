package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressPostDto {
    @NotNull
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String country;
    @NotNull
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String city;
    @NotNull
    @Pattern(regexp = "(^[a-zа-яA-ZА-Я0-9]+(?:[\\s-.][a-zа-яA-ZА-Я]+)*$)*([-]*)")
    private String street;
    private String building;
}
