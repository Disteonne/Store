package com.netcracker.store.mapper;

import com.netcracker.store.API.UserService;
import com.netcracker.store.dto.ProfileDto;
import com.netcracker.store.dto.UserProfilePatchDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.service.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapstructMapper {

    UserProfileMapstructMapper USER_PROFILE_MAPSTRUCT_MAPPER= Mappers.getMapper(UserProfileMapstructMapper.class);

    @Mapping(source = "user.id" ,target = "userId")
    ProfileDto toProfileDto(User user, Address address);

    default  User mapToUser(UserProfilePatchDto userProfilePatchDto, String currentUserLogin, UserService userServiceImpl) {
        User user = userServiceImpl.findByLogin(currentUserLogin);
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
