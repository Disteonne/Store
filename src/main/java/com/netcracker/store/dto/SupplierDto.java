package com.netcracker.store.dto;

import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private int id;

    private String name;

    private String mail;

    private List<Product> product;

    private Address address;
}
