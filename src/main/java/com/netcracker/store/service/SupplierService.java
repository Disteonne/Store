package com.netcracker.store.service;

import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier getByAll(String name, String mail, Address address){
        return supplierRepository.findByNameAndMailAndAddress(name,mail,address);
    }

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAll(String name,int page, int size, Sort sort){
        if(name==null) {
            Page<Supplier> result = supplierRepository.findAll(PageRequest.of(page, size, sort));
            return result.getContent();
        }
        return supplierRepository.findSupplierByNameStartingWith(name,PageRequest.of(page,size,sort));
    }
}
