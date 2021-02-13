package com.netcracker.store.service;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.repository.ProductDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoService {

    private final ProductDtoRepository productDtoRepository;


    public ProductDtoService(ProductDtoRepository productDtoRepository) {
        this.productDtoRepository = productDtoRepository;
    }

    public String saveDto(ProductDto productDto) {
        productDtoRepository.save(productDto);
        return "saved";
    }

    public String deleteDto(ProductDto productDto) {
        productDtoRepository.delete(productDto);
        return "deleted";
    }

    public String fullUpdate(String name,String type, double price,int count, int supplierId,String info,int id) throws NotFoundException {
        ProductDto productDto=find(id);
        productDto.setName(name);
        productDto.setType(type);
        productDto.setPrice(price);
        productDto.setCount(count);
        productDto.setSupplierId(supplierId);
        productDto.setInfo(info);
        productDtoRepository.save(productDto);
        return "ok";
    }

    public String updatePart(String whatUpdate,String infoToUpdate,int id) throws NotFoundException, ResponseInputException {
        ProductDto productDto=find(id);
        switch (whatUpdate){
            case "name":
                productDto.setName(infoToUpdate);
                break;
            case "type":
                productDto.setType(infoToUpdate);;
                break;
            case "price":
                productDto.setPrice(Integer.parseInt(infoToUpdate));
                break;
            case "count":
                productDto.setCount(Integer.parseInt(infoToUpdate));
                break;
            case "supplierId":
                productDto.setSupplierId(Integer.parseInt(infoToUpdate));
                break;
            case "info":
                productDto.setInfo(infoToUpdate);
                break;
            default:
                throw  new ResponseInputException("Error entering the change field");
        }
        productDtoRepository.save(productDto);
        return "save";
    }

    private ProductDto find(int id) throws NotFoundException {
        return productDtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
