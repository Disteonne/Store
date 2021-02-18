package com.netcracker.store.service;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier getById(Long id) {
        return supplierRepository.getOne(id);
    }

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public boolean deleteById(Long id){
       try {
           supplierRepository.deleteById(id);
       }catch (Exception ex){
           return  false;
       }
       return true;
    }

    public List<Supplier> getAll(int page, int size, Sort sort){
        Page<Supplier> result = supplierRepository.findAll(PageRequest.of(page,size,sort));
        return result.getContent();
    }
}
