package com.netcracker.store.mapper;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.AddressException;
import com.netcracker.store.exception.InputException;
import com.netcracker.store.exception.SupplierException;
import com.netcracker.store.exception.TypeNotFoundException;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.ProductService;
import com.netcracker.store.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WarehouseMapper {

    private final ProductMapper productMapper;
    private final AddressMapper addressMapper;
    private final SupplierMapper supplierMapper;

    private ProductService productService;
    private SupplierService supplierService;
    private AddressService addressService;

    //тз на 07.03.21
    //напиши проверку на ввод + учти наличие поставщика и адреса в бд
    //оптимизиуй код
    public Address toAddress(WarehousePostDto warehouse) throws InputException {
        AddressPostDto addressPostDto = new AddressPostDto();
        if (warehouse != null) {
            addressPostDto.setCountry(warehouse.getCountry());
            addressPostDto.setCity(warehouse.getCity());
            addressPostDto.setStreet(warehouse.getStreet());
            addressPostDto.setBuilding(warehouse.getBuilding());
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toAddress");
        }
        return addressMapper.toAddress(addressPostDto);
    }

    public Supplier toSupplier(WarehousePostDto warehouse, AddressDto addressDto) throws InputException {
        SupplierPostDto supplierPostDto = new SupplierPostDto();
        if (warehouse != null && addressDto != null) {
            supplierPostDto.setName(warehouse.getSupplierName());
            supplierPostDto.setMail(warehouse.getMail());
            supplierPostDto.setAddressId(addressDto.getId());
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toSupplier");
        }
        return supplierMapper.toSupplier(supplierPostDto);
    }

    public Product toProduct(WarehousePostDto warehouse, SupplierDto supplierDto) throws TypeNotFoundException, InputException {
        ProductPostDto productPostDto = new ProductPostDto();
        if (warehouse != null && supplierDto != null) {
            productPostDto.setName(warehouse.getProductName());
            productPostDto.setPrice(warehouse.getPrice());
            productPostDto.setCount(warehouse.getCount());
            productPostDto.setType(warehouse.getType());
            productPostDto.setInfo(warehouse.getInfo());
            productPostDto.setSupplierId(supplierDto.getId());
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toProduct");
        }
        return productMapper.toProduct(productPostDto);
    }

    public Product toProductNewSupplier(WarehousePatchDto warehouse) throws SupplierException, AddressException {
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
            supplier.setAddress(saveAddress(warehouse));
            Supplier search = supplierService.getByAll(supplier.getName(), supplier.getMail(), supplier.getAddress());
            if (search == null) {
                supplierService.save(supplier);
            } else {
                supplier = search;
            }
            product.setSupplier(supplier);
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
