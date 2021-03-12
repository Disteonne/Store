package com.netcracker.store.mapper;

import com.netcracker.store.dto.UserDto;
import com.netcracker.store.dto.UserPostDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.entity.UsersRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapstructMapper {

    UserMapstructMapper USER_MAPSTRUCT_MAPPER= Mappers.getMapper(UserMapstructMapper.class);

    @Mapping(source = "user.address.id",target = "addressId")
    UserDto mapToUserDto(User user);




    default List<UserDto> toUserDtoList(List<User> all) {
        List<UserDto> result = new ArrayList<>();
        if (all == null) {
            return result;
        }
        all.forEach(user -> result.add(mapToUserDto(user)));
        return result;
    }

    default User toUser(UserPostDto userPostDto) {

        if (userPostDto == null) {
            return null;
        }
        User user = new User();
        user.setName(userPostDto.getName());
        user.setSurname(userPostDto.getSurname());
        user.setAge(userPostDto.getAge());

        Set<UsersRole> usersRoles = new HashSet<>();
        if (userPostDto.getCredentials() == null) {
            usersRoles.add(UsersRole.ROLE_USER);
            user.setUsersRoles(usersRoles);
        } else {
            usersRoles.add(UsersRole.ROLE_ADMIN);
            user.setUsersRoles(usersRoles);
        }
        user.setLogin(userPostDto.getLogin());
        user.setPassword(userPostDto.getPassword());
        Address address = new Address();
        address.setId(userPostDto.getAddressId());
        user.setAddress(address);
        return user;
    }

    default User patch(User user, UserDto userDto) {
        if (userDto == null) {
            return user;
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getSurname() != null) {
            user.setSurname(userDto.getSurname());
        }
        if (userDto.getUsersRoles() != null) {
            Set<UsersRole> usersRoles = new HashSet<>();
            usersRoles.add(UsersRole.ROLE_ADMIN);
            user.setUsersRoles(usersRoles);
            //user.setCredentials(Credentials.ADMIN.toString());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }
        //history not added
        if (userDto.getLogin() != null) {
            user.setLogin(userDto.getLogin());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getAddressId() != null) {
            Address address = new Address();
            address.setId(userDto.getAddressId());
            user.setAddress(address);
        }
        return user;
    }
}
