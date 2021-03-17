package com.netcracker.store.dto;

import com.netcracker.store.entity.UsersRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserPostDto {

    private Set<UsersRole> role= new HashSet<>();//default
    @NotNull
    @Pattern(regexp = "^[А-Я][а-я-]+",message = "Surname is not valid.")
    private String surname;
    @NotNull
    @Pattern(regexp = "^[А-Я][а-я-]+",message = "Name is not valid.")
    private String name;
    @NotNull
    @Min(18)
    private int age;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Login is not valid")
    private String login;
    @NotNull
    private String password;
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Mail is not valid")
    private String mail;

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
