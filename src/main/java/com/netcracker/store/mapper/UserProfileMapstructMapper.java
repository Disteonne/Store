package com.netcracker.store.mapper;

import com.netcracker.store.dto.InfoDto;
import com.netcracker.store.dto.UserProfilePatchDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapstructMapper {

    UserProfileMapstructMapper INFO_MAPSTRUCT_MAPPER= Mappers.getMapper(UserProfileMapstructMapper.class);

    @Mapping(source = "user.id" ,target = "userId")
    InfoDto toInfoDto(User user, Address address);

    default  User mapToUser(UserProfilePatchDto userProfilePatchDto, String currentUserLogin,UserService userService) {
        User user = userService.findByLogin(currentUserLogin);
        if (!userProfilePatchDto.getName().equals("")) {
            user.setName(userProfilePatchDto.getName());
        }
        if (!userProfilePatchDto.getSurname().equals("")) {
            user.setSurname(userProfilePatchDto.getSurname());
        }
        if (userProfilePatchDto.getAge() != null) {
            user.setAge(userProfilePatchDto.getAge());
        }
        return user;
    }
}
