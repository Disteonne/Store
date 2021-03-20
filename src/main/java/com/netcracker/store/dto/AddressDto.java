package com.netcracker.store.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressDto {


    private Long id;
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
