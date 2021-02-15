package com.netcracker.store.service;

import com.netcracker.store.check.CheckForSortAndPaging;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.User;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.repository.UserPaginationAndSortRepository;
import com.netcracker.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPaginationAndSortRepository userPaginationAndSortRepository;

    private final CheckForSortAndPaging check=new CheckForSortAndPaging();

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public List<User> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<User> getUserById(int id) throws NotFoundException {
        return ResponseEntity.ok().body(find(id));
    }

    public Map<String, Boolean> saveUser(User user) {
        Address address = user.getAddress();
        if (address.getCountry() == null && address.getCity() == null && address.getStreet() == null) {
            address = null;
            user.setAddress(address);
        } else {
            addressService.saveAddress(address); //адрес перезаписывается,если уже есть такой?
        }
        repository.save(user);
        Map<String, Boolean> result = new HashMap<>();
        result.put("saved", true);
        return result;
    }

    public Map<String, Boolean> deleteUser(User user) {
        repository.delete(user);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;
    }

    public Map<String, Boolean> deleteUserById(int id) throws NotFoundException {
        User user = find(id);
        repository.delete(user);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return result;
    }

    private User find(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<User> sortAndPaging(int page,int size,String sortBy,String sortOrder){
       Page<User> result = userPaginationAndSortRepository.findAll(check.returnPage(page,size,sortBy,sortOrder));
        if (!result.isEmpty()) {
            return result.getContent();
        } else {
            return new ArrayList<User>();
        }
    }
}
