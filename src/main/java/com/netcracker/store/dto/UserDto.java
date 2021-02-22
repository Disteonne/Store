package com.netcracker.store.dto;

import com.netcracker.store.entity.Credential;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;
    private Set<Credential> credentials; //was String
    private String surname;
    private String name;
    private Integer age;
    private String login;
    private String password;
    private Long addressId;
    private Long historyId;

}
