package com.netcracker.store.service;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public List<Supplier> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<Supplier> getSupplierById(int id) throws NotFoundException {
        return ResponseEntity.ok().body(find(id));
    }

    public Map<String, Boolean> saveSupplier(Supplier supplier) {
        repository.save(supplier);
        Map<String, Boolean> result = new HashMap<>();
        result.put("saved", true);
        return result;
    }

    public Map<String, Boolean> deleteSupplier(Supplier supplier) {
        repository.delete(supplier);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;
    }

    public Map<String, Boolean> deleteSupplierById(int id) throws NotFoundException {
        Supplier supplier=find(id);
        repository.deleteById(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return result;
    }

    private Supplier find(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
    }

}
