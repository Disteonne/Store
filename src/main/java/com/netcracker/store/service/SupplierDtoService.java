package com.netcracker.store.service;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.repository.SupplierDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierDtoService {

    private final SupplierDtoRepository supplierDtoRepository;

    public SupplierDtoService(SupplierDtoRepository supplierDtoRepository) {
        this.supplierDtoRepository = supplierDtoRepository;
    }


    public String saveDto(SupplierDto supplierDto) {
        supplierDtoRepository.save(supplierDto);
        return "saved";
    }

    public String deleteDto(SupplierDto supplierDto) {
        supplierDtoRepository.delete(supplierDto);
        return "deleted";
    }

    public String fullUpdate(String name, String mail, int  addressId, int id) throws NotFoundException {
        SupplierDto supplierDto=find(id);
        supplierDto.setName(name);
        supplierDto.setMail(mail);
        supplierDto.setAddressId(addressId);
        supplierDtoRepository.save(supplierDto);
        return "ok";
    }

    public String updatePart(String whatUpdate,String infoToUpdate,int id) throws NotFoundException, ResponseInputException {
        SupplierDto supplierDto=find(id);
        switch (whatUpdate){
            case "name":
                supplierDto.setName(infoToUpdate);
                break;
            case "mail":
                supplierDto.setMail(infoToUpdate);
                break;
            case "addressId":
                supplierDto.setAddressId(Integer.parseInt(infoToUpdate));
                break;
            default:
                throw  new ResponseInputException("Error entering the change field");
        }
        supplierDtoRepository.save(supplierDto);
        return "save";
    }

    private SupplierDto find(int id) throws NotFoundException {
        return supplierDtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
    }
}
