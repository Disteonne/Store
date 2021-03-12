package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.ProfileDto;
import com.netcracker.store.dto.UserProfilePatchDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;

    public ProfileDto toInfoDto(User user, Address address) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserId(user.getId());
        profileDto.setName(user.getName());
        profileDto.setSurname(user.getSurname());
        profileDto.setAge(user.getAge());
        profileDto.setLogin(user.getLogin());
        profileDto.setCountry(address.getCountry());
        profileDto.setCity(address.getCity());
        profileDto.setStreet(address.getStreet());
        profileDto.setBuilding(address.getBuilding());
        return profileDto;
    }

    public User toUser(UserProfilePatchDto userProfilePatchDto, String currentUserLogin) {
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

    public Address toAddress(UserProfilePatchDto userProfilePatchDto, String currentUserLogin) {
        //получили текущий адрес
        AddressDto address = addressMapper.toAddressDto(addressService.getById(userMapper.toUserDto(toUser(userProfilePatchDto, currentUserLogin)).getAddressId()));
        if (!userProfilePatchDto.getCountry().equals("")) {
            address.setCountry(userProfilePatchDto.getCountry());
        }
        if (!userProfilePatchDto.getCity().equals("")) {
            address.setCity(userProfilePatchDto.getCity());
        }
        if (!userProfilePatchDto.getStreet().equals("")) {
            address.setStreet(userProfilePatchDto.getStreet());
        }
        if (!userProfilePatchDto.getBuilding().equals("")) {
            address.setBuilding(userProfilePatchDto.getBuilding());
        }
        //находим.есть ли адрес с такими данными
        Address updatedAddress = addressService.find(address.getCountry(), address.getCity(), address.getStreet(), address.getBuilding());
        if (updatedAddress != null) {
            return updatedAddress;
        } else {
            //создаем постDto-без id
            return addressMapper.toAddress(addressMapper.toAddressPostDto(address));
        }
    }

}
