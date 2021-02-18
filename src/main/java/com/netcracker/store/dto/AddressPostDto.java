package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressPostDto {
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    private String building;
}
