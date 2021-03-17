package com.netcracker.store.mapper;

import com.netcracker.store.dto.UserDto;
import com.netcracker.store.dto.UserPostDto;
import com.netcracker.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

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

    @Mapping(source = "userPostDto.country",target = "address.country")
    @Mapping(source = "userPostDto.city",target = "address.city")
    @Mapping(source = "userPostDto.street",target = "address.street")
    @Mapping(source = "userPostDto.building",target = "address.building")
    @Mapping(source = "userPostDto.role",target = "usersRoles")
    User toUser(UserPostDto userPostDto);
}
