package com.netcracker.store.dto;

import com.netcracker.store.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;

    private String name;

    private String type;

    private double price;

    private int count;

    private Supplier supplier;

    private String info;
}
