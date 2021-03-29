package com.netcracker.store.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SupplierDto {

    private Long id;
    @NotNull
    @Pattern(regexp = "^[А-ЯA-Z][a-zа-я-\\s.]+",message = "Surname is not valid.")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Mail is not valid")
    private String mail;
    private Long addressId; //в гет это не нужно

    private String country;
    private String city;
    private String street;
    private String building;
}
