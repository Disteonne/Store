package com.netcracker.store.dto;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Integer id;

    private String country;

    private String city;

    private String street;

    private String building;

    private List<User> user;

    private List<Supplier> supplier;
}
