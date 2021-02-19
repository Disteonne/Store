package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class SupplierPostDto {

    @NotNull
    private String name;
    @NotNull
    private String mail;
    @NotNull
    private Long addressId;
}
