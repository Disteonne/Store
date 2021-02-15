package com.netcracker.store.controller;

import com.netcracker.store.check.CheckForPatchMapping;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.entity.User;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.service.UserDtoService;
import com.netcracker.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserDtoService userDtoService;

    private final CheckForPatchMapping checkForPatchMapping = new CheckForPatchMapping();

    @GetMapping("/getAll")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") int id) throws NotFoundException {
        return service.getUserById(id);
    }

    @PostMapping("/save")
    public Map<String, Boolean> save(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/saveDto")
    public String saveDto(@RequestBody UserDto userDto) {
        return userDtoService.saveDto(userDto);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody UserDto userDto) {
        return userDtoService.deleteDto(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteById(@PathVariable(value = "id") int id) throws NotFoundException {
        return service.deleteUserById(id);
    }

    @PutMapping("/update/put")
    public ResponseEntity<String> putUpdate(@RequestBody UserDto userDto) {
        userDtoService.saveDto(userDto);
        return ResponseEntity.ok("Saved");
    }

    @PatchMapping("/update/patch")
    public ResponseEntity<String> patchUpdate(@RequestBody UserDto userDto) throws IllegalAccessException, NotFoundException, ResponseInputException {
        Map<String, String> fields = checkForPatchMapping.validateObject(userDto);
        for (Map.Entry<String, String> pair : fields.entrySet()
        ) {
            userDtoService.updatePart(pair.getKey(), pair.getValue(), userDto.getId());
        }
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/update/surname={surname}&name={name}&age={age}&login={login}&" +
            "password={password}&mail={mail}&addressId={addressId}&id={id}")
    public String fullUpdate(@Valid @PathVariable(value = "surname") String surname,
                             @Valid @PathVariable(value = "name") String name,
                             @Valid @PathVariable(value = "age") int age,
                             @Valid @PathVariable(value = "login") String login,
                             @Valid @PathVariable(value = "password") String password,
                             @Valid @PathVariable(value = "mail") String mail,
                             @Valid @PathVariable(value = "addressId") int addressId,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException {
        return userDtoService.fullUpdate(surname, name, age, login, password, mail, addressId, id);
    }

    @PatchMapping("/update/whatUpdate={what}&toUpdate={to}&id={id}")
    public String partUpdate(@Valid @PathVariable(value = "what") String whatUpdate,
                             @Valid @PathVariable(value = "to") String toUpdate,
                             @Valid @PathVariable(value = "id") int id) throws NotFoundException, ResponseInputException {
        return userDtoService.updatePart(whatUpdate, toUpdate, id);
    }
}
