package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String credentials;
    private String surname;
    private String name;
    private Integer age;
    private String login;
    private String password;
    private String mail;
    private Long addressId;
    private Long historyId;

}
