package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class UserPutDto {

    @NotNull
    private Long id;
    private String credentials;
    @NotNull
    private String surname;
    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String mail;
    @NotNull
    private Long addressId;
}
