package com.netcracker.store.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PasswordDto {
    @NotNull
    @NotEmpty
    private String password;
}
