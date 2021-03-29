package com.netcracker.store.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    private Long id;
    @NotNull
    @Pattern(regexp = "^[А-ЯA-Z][а-яa-z-]+",message = "Name is not valid.")
    private String name;
    @NotNull
    @Pattern(regexp = "^[А-ЯA-Z][а-яa-z-]+",message = "Surname is not valid.")
    private String surname;
    @NotNull
    @Min(18)
    private Integer age;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Login is not valid")
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Mail is not valid")
    private String mail;
    private Long addressId;
    //private Long historyId; //убери

    @NotNull
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String country;
    @NotNull
    @Pattern(regexp = "^[a-zа-яA-ZА-Я]+(?:[\\s-][a-zа-яA-ZА-Я]+)*$")
    private String city;
    @NotNull
    @Pattern(regexp = "(^[a-zа-яA-ZА-Я0-9]+(?:[\\s-.][a-zа-яA-ZА-Я]+)*$)*([-]*)")
    private String street;
    private String building;

}
