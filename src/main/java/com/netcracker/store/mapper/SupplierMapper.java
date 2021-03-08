package com.netcracker.store.mapper;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.dto.SupplierGetDto;
import com.netcracker.store.dto.SupplierPostDto;
import com.netcracker.store.dto.SupplierPutDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Supplier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierMapper {

    public SupplierDto toSupplierDto(Supplier supplier){
        if(supplier==null){
            return null;
        }
        SupplierDto supplierDto=new SupplierDto();
        supplierDto.setId(supplier.getId());
        supplierDto.setName(supplier.getName());
        supplierDto.setMail(supplier.getMail());
        if(supplier.getAddress()!=null){
            supplierDto.setAddressId(supplier.getAddress().getId());
        }
        return supplierDto;
    }

    public List<SupplierDto> toSupplierDtoList(List<Supplier> all) {
        List<SupplierDto> result=new ArrayList<>();
        if(all==null){
            return result;
        }
        all.forEach(supplier -> result.add(toSupplierDto(supplier)));
        return result;
    }

    public Supplier toSupplier(SupplierPostDto supplierPostDto) {
        if(supplierPostDto==null){
            return null;
        }
        Supplier supplier=new Supplier();
        supplier.setName(supplierPostDto.getName());
        supplier.setMail(supplierPostDto.getMail());
        Address address=new Address();
        address.setId(supplierPostDto.getAddressId());
        supplier.setAddress(address);
        return supplier;
    }

    public Supplier toSupplier(SupplierPutDto supplierPutDto) {
        if(supplierPutDto==null){
            return null;
        }
        Supplier supplier=new Supplier();
        supplier.setId(supplierPutDto.getId());
        supplier.setName(supplierPutDto.getName());
        supplier.setMail(supplierPutDto.getMail());
        Address address=new Address();
        address.setId(supplierPutDto.getAddressId());
        supplier.setAddress(address);
        return supplier;
    }

    public Supplier patch(Supplier supplier, SupplierDto supplierDto) {
        if(supplierDto==null){
            return supplier;
        }
        if(supplierDto.getName()!=null){
            supplier.setName(supplierDto.getName());
        }
        if(supplierDto.getMail()!=null){
            supplier.setMail(supplierDto.getMail());
        }
        if(supplierDto.getAddressId()!=null){
            Address address=new Address();
            address.setId(supplierDto.getAddressId());
            supplier.setAddress(address);
        }
        return supplier;
    }

    public SupplierGetDto toSupplierGetDto(Supplier supplier){
        if(supplier==null){
            return null;
        }
        SupplierGetDto supplierGetDto=new SupplierGetDto();
        supplierGetDto.setId(supplierGetDto.getId());
        supplierGetDto.setName(supplier.getName());
        supplierGetDto.setMail(supplier.getMail());
        supplierGetDto.setCountry(supplier.getAddress().getCountry());
        supplierGetDto.setCity(supplier.getAddress().getCity());
        supplierGetDto.setStreet(supplier.getAddress().getStreet());
        supplierGetDto.setBuilding(supplier.getAddress().getBuilding());
        return supplierGetDto;
    }

    public List<SupplierGetDto> toSupplierGetDtoList(List<Supplier> suppliers){
        List<SupplierGetDto> result=new ArrayList<>();
        if(suppliers==null){
            return result;
        }
        suppliers.forEach(supplier -> result.add(toSupplierGetDto(supplier)));
        return result;
    }
}
