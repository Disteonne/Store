package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class AddressPutDto {
    @NonNull
    private Long id;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    private String building;
}
