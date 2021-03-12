package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long userId;
    private String surname;
    private String name;
    private Integer age;
    private String login;
    private String password;
    private String country;
    private String city;
    private String street;
    private String building;
}
