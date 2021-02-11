package com.netcracker.store.controller;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.SupplierRepository;
import com.netcracker.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Supplier> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable(value= "id") int id){
        return service.getSupplierById(id);
    }

    @PostMapping("/save")
    public Map<String,Boolean> save(@RequestBody Supplier supplier){
        return service.saveSupplier(supplier);
    }

    @DeleteMapping("/delete")
    public Map<String,Boolean> delete(@RequestBody Supplier supplier){
        return service.deleteSupplier(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteById(@PathVariable(value = "id") int id){
        return service.deleteSupplierById(id);
    }


}
