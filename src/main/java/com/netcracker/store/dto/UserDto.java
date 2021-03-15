package com.netcracker.store.dto;

import com.netcracker.store.entity.UsersRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;
    private Set<UsersRole> usersRoles; //was String
    private String surname;
    private String name;
    private Integer age;
    private String login;
    private String password;
    //private String mail;
    private Long addressId;
    private Long historyId; //убери

}
