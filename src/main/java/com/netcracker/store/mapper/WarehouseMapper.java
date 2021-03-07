package com.netcracker.store.mapper;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.*;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.ProductService;
import com.netcracker.store.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WarehouseMapper {


    private ProductService productService;
    private SupplierService supplierService;
    private AddressService addressService;

    public Address toAddress(WarehousePatchDto warehouse) throws InputException, AddressException {

        if (warehouse != null) {
            return saveAddress(warehouse);
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toAddress");
        }
    }
    public Supplier toSupplier(WarehousePatchDto warehouse) throws InputException, SupplierException, AddressException {
        if(warehouse!=null){
            return  newSupplier(warehouse);
        }else {
            throw new InputException("Input exception: WarehouseMapper-> toSupplier");
        }
    }

    public Product toProduct(WarehousePatchDto warehouse) throws InputException, ProductException, SupplierException, AddressException {
        if(warehouse!=null){
            Product product=new Product();
            if(productService.getByName(warehouse.getProductOldName())!=null){
                throw new InputException("Product in db already");
            }else {
                if(warehouse.getProductOldName().equals("")){
                    throw new ProductException("Product name IS NULL");
                }else {
                    product.setName(warehouse.getProductOldName());
                }

                if(warehouse.getType().equals("")){
                    throw new ProductException("Product type IS NULL");
                }else {
                    product.setType(warehouse.getType());
                }

                if(warehouse.getCount()==0){
                    throw new ProductException("Product count IS NULL");
                }else {
                    product.setCount(warehouse.getCount());
                }

                if(warehouse.getPrice()==null){
                    throw new ProductException("Product price IS NULL");
                }else {
                    product.setPrice(warehouse.getPrice());
                }

                if(warehouse.getInfo().equals("")){
                    throw new ProductException("Product info IS NULL");
                }else {
                    product.setInfo(warehouse.getInfo());
                }
                product.setSupplier(newSupplier(warehouse));
            }
            return product;
        }else {
            return new Product();
        }
    }

    public Product toProductNewSupplier(WarehousePatchDto warehouse) throws SupplierException, AddressException, InputException {
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

            product.setSupplier(newSupplier(warehouse));
            return productService.save(product);
        }
        return new Product();
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

    private Supplier newSupplier(WarehousePatchDto warehouse) throws SupplierException, AddressException, InputException {
        Supplier supplier = new Supplier();
        if (warehouse.getSupplierName().equals("")) {
            throw new SupplierException("Supplier name IS NULL");
        } else {
            supplier.setName(warehouse.getSupplierName());
        }
        if (warehouse.getMail().equals("")) {
            throw new SupplierException("Supplier mail IS NULL");
        } else {
            supplier.setMail(warehouse.getMail());
        }
        supplier.setAddress(toAddress(warehouse));
        //supplier.setAddress(saveAddress(warehouse));
        Supplier search = supplierService.getByAll(supplier.getName(), supplier.getMail(), supplier.getAddress());
        if (search == null) {
            supplierService.save(supplier);
        } else {
            supplier = search;
        }
        return supplier;
    }

    private Address saveAddress(WarehousePatchDto warehouse) throws AddressException {
        Address address = addressService.find(warehouse.getCountry(), warehouse.getCity(), warehouse.getStreet(), warehouse.getBuilding());
        if (address == null) {
            address = new Address();
            if (warehouse.getCountry().equals("")) {
                throw new AddressException("Address country IS NULL");
            } else {
                address.setCountry(warehouse.getCountry());
            }
            if (warehouse.getCity().equals("")) {
                throw new AddressException("Address city IS NULL");
            } else {
                address.setCity(warehouse.getCity());
            }
            if (warehouse.getStreet().equals("")) {
                throw new AddressException("Address street IS NULL");
            } else {
                address.setStreet(warehouse.getStreet());
            }
            address.setBuilding(warehouse.getBuilding());
            addressService.save(address);
        }
        return address;
    }
}
