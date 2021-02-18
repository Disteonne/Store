package com.netcracker.store.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

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
    @Min(18)
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