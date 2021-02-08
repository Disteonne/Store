package com.netcracker.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    private long id;

    private String model;

    private String os;

    private Color color;

}
