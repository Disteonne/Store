package com.netcracker.store.entity;

public enum ProductType {

   Technic("Техника и электроника"),Vegetables("Овощи"),Fruits("Фрукты"),Milk("Молочные продукты"),
    VAF("Овощи и фрукты");

   private String info;

    ProductType(String info) {
        this.info = info;
    }
}
