package com.netcracker.store.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private Long userId;
    private String surname;
    private String name;
    private Integer age;
    private String login;
    private String password;
    private String mail;
    private String country;
    private String city;
    private String street;
    private String building;
}
