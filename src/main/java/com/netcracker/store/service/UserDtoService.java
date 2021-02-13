package com.netcracker.store.service;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Credentials;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.repository.UserDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoService {

    private final UserDtoRepository userDtoRepository;

    public UserDtoService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }

    public String saveDto(UserDto userDto) {
        if(userDto.getCredentials()==null){
            userDto.setCredentials(Credentials.USER.toString());
        }
        userDtoRepository.save(userDto);
        return "saved";
    }

    public String deleteDto(UserDto userDto) {
        userDtoRepository.delete(userDto);
        return "deleted";
    }

    public String fullUpdate(String surname,String name, int age, String login,String password,String mail,
                             int addressId,int id) throws NotFoundException {
        UserDto userDto=find(id);
        userDto.setSurname(surname);
        userDto.setName(name);
        userDto.setAge(age);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setMail(mail);
        userDto.setAddressId(addressId);
        userDtoRepository.save(userDto);
        return "ok";
    }

    public String updatePart(String whatUpdate,String infoToUpdate,int id) throws NotFoundException, ResponseInputException {
        UserDto userDto=find(id);
        switch (whatUpdate){
            case "surname":
                userDto.setSurname(infoToUpdate);
                break;
            case "name":
                userDto.setName(infoToUpdate);
                break;
            case "age":
                userDto.setAge(Integer.parseInt(infoToUpdate));
                break;
            case "login":
                userDto.setLogin(infoToUpdate);
                break;
            case "password":
                userDto.setPassword(infoToUpdate);
                break;
            case "mail":
                userDto.setMail(infoToUpdate);
                break;
            case "addressId":
                userDto.setAddressId(Integer.parseInt(infoToUpdate));
            default:
                throw  new ResponseInputException("Error entering the change field");
        }
        userDtoRepository.save(userDto);
        return "save";
    }

    private UserDto find(int id) throws NotFoundException {
        return userDtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
