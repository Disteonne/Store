package com.netcracker.store.mapper;

import com.netcracker.store.dto.UserDto;
import com.netcracker.store.dto.UserPostDto;
import com.netcracker.store.dto.UserPutDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Credentials;
import com.netcracker.store.entity.History;
import com.netcracker.store.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setMail(user.getMail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setAge(user.getAge());
        userDto.setCredentials(user.getCredentials());
        userDto.setMail(user.getMail());
        if (user.getAddress() != null) {
            userDto.setAddressId(user.getAddress().getId());
        }
        if (!user.getHistory().isEmpty()) {
            String history="";
            for (History h: user.getHistory()
                 ) {
                history="ID: "+h.getId()+" ,Date: "+h.getDate()+", List: "+h.getHistory().toString();
            }
            //userDto.setHistoryId(user.getHistory().getId());
        }
        return userDto;
    }

    public List<UserDto> toUserDtoList(List<User> all) {
        List<UserDto> result = new ArrayList<>();
        if (all == null) {
            return result;
        }
        all.forEach(user -> result.add(toUserDto(user)));
        return result;
    }

    public User toUser(UserPostDto userPostDto) {
        if (userPostDto == null) {
            return null;
        }
        User user = new User();
        user.setName(userPostDto.getName());
        user.setSurname(userPostDto.getSurname());
        user.setAge(userPostDto.getAge());
        if (userPostDto.getCredentials() == null) {
            user.setCredentials(Credentials.USER.toString());
        } else {
            user.setCredentials(Credentials.ADMIN.toString());
        }
        user.setLogin(userPostDto.getLogin());
        user.setPassword(userPostDto.getPassword());
        user.setMail(userPostDto.getMail());
        Address address = new Address();
        address.setId(userPostDto.getAddressId());
        user.setAddress(address);
        return user;
    }

    public User toUser(UserPutDto userPutDto) {
        if (userPutDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userPutDto.getId());
        user.setName(userPutDto.getName());
        user.setSurname(userPutDto.getSurname());
        user.setAge(userPutDto.getAge());
        if (userPutDto.getCredentials() == null) {
            user.setCredentials(Credentials.USER.toString());
        } else {
            user.setCredentials(Credentials.ADMIN.toString());
        }
        user.setLogin(userPutDto.getLogin());
        user.setPassword(userPutDto.getPassword());
        user.setMail(userPutDto.getMail());
        Address address = new Address();
        address.setId(userPutDto.getAddressId());
        user.setAddress(address);
        return user;
    }

    public User patch(User user, UserDto userDto) {
        if (userDto == null) {
            return user;
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getSurname() != null) {
            user.setSurname(userDto.getSurname());
        }
        if (userDto.getCredentials() != null) {
            user.setCredentials(Credentials.ADMIN.toString());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }
        if (userDto.getMail() != null) {
            user.setMail(userDto.getMail());
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
