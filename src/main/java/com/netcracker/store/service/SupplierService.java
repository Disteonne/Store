package com.netcracker.store.service;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<Supplier> getAll(){
        return repository.findAll();
    }

    public Supplier getSupplierById(int id){
        return repository.getOne(id);
    }

    public Map<String,Boolean> saveSupplier(Supplier supplier){
        repository.save(supplier);
        Map<String,Boolean> result=new HashMap<>();
        result.put("saved",true);
        return result;
    }

    public Map<String,Boolean> deleteSupplier(Supplier supplier){
        repository.delete(supplier);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }

    public Map<String,Boolean> deleteSupplierById(int id){
        repository.deleteById(id);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }

}
