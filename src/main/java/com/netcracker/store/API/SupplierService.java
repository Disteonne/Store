package com.netcracker.store.API;

import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Supplier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {

    List<Supplier> getAll();

    Supplier getByAll(String name, String mail, Address address);

    Supplier getSupplier(Supplier supplier);

    Supplier getById(Long id);
    Supplier save(Supplier supplier);

    List<Supplier> getAll(String name,int page, int size, Sort sort);
}
