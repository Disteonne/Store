package com.netcracker.store.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
public class SupplierDto {

    private Long id;
    private String name;
    private String mail;
    private Long addressId;
}
