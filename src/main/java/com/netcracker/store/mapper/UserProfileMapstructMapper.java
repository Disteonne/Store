package com.netcracker.store.mapper;

import com.netcracker.store.API.UserService;
import com.netcracker.store.dto.ProfileDto;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.entity.UsersRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserProfileMapstructMapper {

    UserProfileMapstructMapper USER_PROFILE_MAPSTRUCT_MAPPER= Mappers.getMapper(UserProfileMapstructMapper.class);

    @Mapping(source = "user.id" ,target = "userId")
    @Mapping(source = "user.mail",target = "mail")
    ProfileDto toProfileDto(User user, Address address);

    @Mapping(source = "userDto.addressId",target = "address.id")
    @Mapping(source = "role",target = "usersRoles")
    User mapToUserTwo(UserDto userDto, Set<UsersRole> role);

}
