package com.netcracker.store.mapper;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.*;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.ProductService;
import com.netcracker.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private AddressService addressService;

    /*
    Создаем новый продукт и возвращаем
    в сервисе проверяем на наличие данного продукта в бд
     */
    public Product toProduct(WarehousePostDto warehouse) {
        if (warehouse == null) {
            return null;
        }
        Product product = new Product();
        product.setName(warehouse.getProductName());
        product.setInfo(warehouse.getInfo());
        product.setPrice(warehouse.getPrice());
        product.setCount(warehouse.getCount());
        product.setType(warehouse.getType());

        Supplier supplier = new Supplier();
        supplier.setName(warehouse.getSupplierName());
        supplier.setMail(warehouse.getMail());

        Address address = new Address();
        address.setCountry(warehouse.getCountry());
        address.setCity(warehouse.getCity());
        address.setStreet(warehouse.getStreet());
        address.setBuilding(warehouse.getBuilding());

        supplier.setAddress(address);
        product.setSupplier(supplier);
        return product;
    }

    public Product toProductNewSupplier(WarehouseNewSuppDto warehouse) throws ProductException {
        Product product = productService.getByName(warehouse.getProductOldName());
        if (product == null) {
            throw new ProductException("Product not founded");
        }
        if (!warehouse.getProductNewName().equals(""))
            product.setName(warehouse.getProductNewName());
        if (warehouse.getCount() != 0)
            product.setCount(warehouse.getCount());
        if (warehouse.getPrice() != null)
            product.setPrice(warehouse.getPrice());
        if (!warehouse.getType().equals(""))
            product.setType(warehouse.getType());
        if (!warehouse.getInfo().equals(""))
            product.setInfo(warehouse.getInfo());

        Supplier supplier = new Supplier();
        supplier.setName(warehouse.getSupplierName());
        supplier.setMail(warehouse.getMail());

        Address address = new Address();
        address.setCountry(warehouse.getCountry());
        address.setCity(warehouse.getCity());
        address.setStreet(warehouse.getStreet());
        address.setBuilding(warehouse.getBuilding());

        supplier.setAddress(address);
        product.setSupplier(supplier);
        return product;
    }

    public Product toProductEditSupplier(WarehousePatchDto warehouse) {
        Product product = productService.getByName(warehouse.getProductOldName());
        if (product != null) {
            if (!warehouse.getProductNewName().equals(""))
                product.setName(warehouse.getProductNewName());
            if (warehouse.getCount() != 0)
                product.setCount(warehouse.getCount());
            if (warehouse.getPrice() != null)
                product.setPrice(warehouse.getPrice());
            if (!warehouse.getType().equals(""))
                product.setType(warehouse.getType());
            if (!warehouse.getInfo().equals(""))
                product.setInfo(warehouse.getInfo());

            Supplier supplier = product.getSupplier();
            if (!warehouse.getSupplierName().equals(""))
                supplier.setName(warehouse.getSupplierName());
            if (!warehouse.getMail().equals(""))
                supplier.setMail(warehouse.getMail());

            Address address = addressService.find(warehouse.getCountry(), warehouse.getCity(), warehouse.getStreet(), warehouse.getBuilding());
            if (address == null) {
                address = new Address();
                byte count = 0;
                if (!warehouse.getCountry().equals(""))
                    address.setCountry(warehouse.getCountry());
                else {
                    count++;
                    address.setCountry(supplier.getAddress().getCountry());
                }
                if (!warehouse.getCity().equals(""))
                    address.setCity(warehouse.getCity());
                else {
                    count++;
                    address.setCity(supplier.getAddress().getCity());
                }
                if (!warehouse.getStreet().equals(""))
                    address.setStreet(warehouse.getStreet());
                else {
                    count++;
                    address.setStreet(supplier.getAddress().getStreet());
                }
                if (!warehouse.getBuilding().equals(""))
                    address.setBuilding(warehouse.getBuilding());
                else {
                    count++;
                    address.setBuilding(supplier.getAddress().getBuilding());
                }
                if (count == 4)
                    address.setId(supplier.getAddress().getId());
                addressService.save(address);
            }
            supplier.setAddress(address);
            supplierService.save(supplier);
            product.setSupplier(supplier);
            return productService.save(product);
        }
        return new Product();
    }
}
