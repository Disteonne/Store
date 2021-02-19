package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

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
