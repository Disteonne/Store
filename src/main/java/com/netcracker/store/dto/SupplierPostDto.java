package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SupplierPostDto {

    @NotNull
    @Pattern(regexp = "^[А-ЯA-Z][a-zа-я-\\s.]+",message = "Surname is not valid.")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Mail is not valid")
    private String mail;
    @NotNull
    private Long addressId;
}
