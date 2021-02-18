package com.netcracker.store.mapper;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.ProductPostDto;
import com.netcracker.store.dto.ProductPutDto;
import com.netcracker.store.entity.Credentials;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setType(product.getType());
        productDto.setCount(product.getCount());
        productDto.setInfo(product.getInfo());
        productDto.setPrice(product.getPrice());
        if (product.getSupplier() != null) {
            productDto.setSupplierId(product.getSupplier().getId());
        }
        return productDto;
    }

    public Product toProduct(ProductPostDto productPostDto) {
        if (productPostDto == null) {
            return null;
        }
        Product product = new Product();
        product.setPrice(productPostDto.getPrice());
        product.setCount(productPostDto.getCount());
        product.setInfo(productPostDto.getInfo());
        product.setName(productPostDto.getName());
        if (productPostDto.getType() == null) {
            product.setType(Credentials.USER.toString());
        } else
            product.setType(Credentials.ADMIN.toString());

        Supplier supplier = new Supplier();
        supplier.setId(productPostDto.getSupplierId());
        product.setSupplier(supplier);
        return product;
    }

    public List<ProductDto> toProductDtoList(List<Product> all) {
        List<ProductDto> result = new ArrayList<>();
        if (all == null) {
            return result;
        }
        all.forEach(product -> result.add(toProductDto(product)));
        return result;
    }

    public Product toProduct(ProductPutDto productPutDto) {
        if (productPutDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productPutDto.getId());
        product.setPrice(productPutDto.getPrice());
        product.setCount(productPutDto.getCount());
        product.setInfo(productPutDto.getInfo());
        product.setName(productPutDto.getName());
        product.setType(productPutDto.getType());

        Supplier supplier = new Supplier();
        supplier.setId(productPutDto.getSupplierId());
        product.setSupplier(supplier);
        return product;
    }

    public Product patch(Product product, ProductDto dto) {
        if(dto==null){
            return null;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
        if(dto.getCount()!=null){
            product.setCount(dto.getCount());
        }
        if(dto.getInfo()!=null){
            product.setInfo(dto.getInfo());
        }
        if(dto.getPrice()!=null){
            product.setPrice(dto.getPrice());
        }
        if(dto.getType()!=null){
            product.setType(dto.getType());
        }
        if(dto.getName()!=null){
            product.setName(dto.getName());
        }
        if(dto.getSupplierId()!=null){
            Supplier supplier=new Supplier();
            supplier.setId(dto.getSupplierId());
            product.setSupplier(supplier);
        }
        return product;
    }
}
