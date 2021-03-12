package com.netcracker.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfilePatchDto {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String surname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;
    private Integer age;
    private String login;
    private String country;
    private String city;
    private String street;
    private String building;
}
