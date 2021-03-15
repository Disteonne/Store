package com.netcracker.store.dto;

import com.netcracker.store.entity.UsersRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserPostDto {

    private String credentials= UsersRole.ROLE_USER.toString();//default
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
    @Pattern(regexp = "[A-Za-z0-9.]+@[a-z0-9]+\\.[a-z]{2,3}" ,message = "Mail is not valid")
    private String login;
    @NotNull
    private String password;
    //private String mail;
    @NotNull
    private Long addressId;

}
