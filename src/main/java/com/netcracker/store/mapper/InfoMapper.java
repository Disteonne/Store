package com.netcracker.store.mapper;

import com.netcracker.store.dto.InfoDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import org.springframework.stereotype.Component;

@Component
public class InfoMapper {

    public InfoDto getInfo(User user, Address address, String login) {
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
}
