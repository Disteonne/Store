package com.netcracker.store.mapper;

import com.netcracker.store.dto.InfoDto;
import com.netcracker.store.dto.InfoPostDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfoMapper {

    private final UserService userService;
    private final AddressService addressService;
    private final UserMapper userMapper;

    public InfoDto toInfoDto(User user, Address address) {
        InfoDto infoDto = new InfoDto();
        infoDto.setUserId(user.getId());
        infoDto.setName(user.getName());
        infoDto.setSurname(user.getSurname());
        infoDto.setAge(user.getAge());
        infoDto.setLogin(user.getLogin());
        infoDto.setCountry(address.getCountry());
        infoDto.setCity(address.getCity());
        infoDto.setStreet(address.getStreet());
        infoDto.setBuilding(address.getBuilding());
        return infoDto;
    }

    public User toUser(InfoPostDto infoPostDto,String currentUserLogin){
        User user=userService.findByLogin(currentUserLogin);
        if(!infoPostDto.getName().equals("")){
            user.setName(infoPostDto.getName());
        }
        if(!infoPostDto.getSurname().equals("")){
            user.setSurname(infoPostDto.getSurname());
        }
        if(infoPostDto.getAge()!=null){
            user.setAge(infoPostDto.getAge());
        }
        return user;
    }
    public Address toAddress(InfoPostDto infoPostDto,String currentUserLogin){
        Address address=addressService.getById(userMapper.toUserDto(toUser(infoPostDto,currentUserLogin)).getAddressId());
        if(!infoPostDto.getCountry().equals("")){
            address.setCountry(infoPostDto.getCountry());
        }
        if(!infoPostDto.getCity().equals("")){
            address.setCity(infoPostDto.getCity());
        }
        if(!infoPostDto.getStreet().equals("")){
            address.setStreet(infoPostDto.getStreet());
        }
        if(!infoPostDto.getBuilding().equals("")){
            address.setBuilding(infoPostDto.getBuilding());
        }
        return address;
    }

}
