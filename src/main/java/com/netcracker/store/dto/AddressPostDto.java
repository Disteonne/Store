package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressPostDto {
    @NotNull
    @Pattern(regexp = "^[A-ZА-Я][a-zа-я-]+")
    private String country;
    @NotNull
    @Pattern(regexp = "^[A-ZА-Я][a-zа-я-]+")
    private String city;
    @NotNull
    @Pattern(regexp = "^[A-ZА-Я][a-zа-я-]+")
    private String street;
    private String building;
}
