package com.netcracker.store.mapper;

import com.netcracker.store.dto.ProductBasketDto;
import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.ProductPostDto;
import com.netcracker.store.dto.ProductPutDto;
import com.netcracker.store.entity.UsersRole;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.TypeNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    //+
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
    //+
    public ProductBasketDto toProductBasketDto(Product product,int count){
        if(product==null){
            return null;
        }
        ProductBasketDto productBasketDto=new ProductBasketDto();
        productBasketDto.setId(product.getId());
        productBasketDto.setCount(count);//кол-во из корзины
        productBasketDto.setName(product.getName());
        productBasketDto.setType(product.getType());
        productBasketDto.setInfo(product.getInfo());
        productBasketDto.setPrice(product.getPrice());
        productBasketDto.setSupplierId(toProductDto(product).getSupplierId());
        return productBasketDto;
    }
    //+
    public Product toProduct(ProductPostDto productPostDto) throws TypeNotFoundException {
        if (productPostDto == null) {
            return null;
        }
        Product product = new Product();
        product.setPrice(productPostDto.getPrice());
        product.setCount(productPostDto.getCount());
        product.setInfo(productPostDto.getInfo());
        product.setName(productPostDto.getName());
        if (productPostDto.getType() == null) {
            throw new TypeNotFoundException("Type is null.");
        } else
            product.setType(productPostDto.getType());

        Supplier supplier = new Supplier();
        supplier.setId(productPostDto.getSupplierId());
        product.setSupplier(supplier);
        return product;
    }
    //+
    public List<ProductDto> toProductDtoList(List<Product> productList) {
        List<ProductDto> result = new ArrayList<>();
        if (productList == null) {
            return result;
        }
        productList.forEach(product -> result.add(toProductDto(product)));
        return result;
    }
    //+
    public Product toProduct(ProductPutDto productPutDto) {
        if (productPutDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productPutDto.getId());
        product.setName(productPutDto.getName());
        product.setPrice(productPutDto.getPrice());
        product.setCount(productPutDto.getCount());
        product.setInfo(productPutDto.getInfo());
        product.setType(productPutDto.getType());

        Supplier supplier = new Supplier();
        supplier.setId(productPutDto.getSupplierId());
        product.setSupplier(supplier);
        String s="";
        return product;
    }

    public Product patch(Product product, ProductDto dto) {
        if (dto == null) {
            return product;
        }
        if (dto.getCount() != null) {
            product.setCount(dto.getCount());
        }
        if (dto.getInfo() != null) {
            product.setInfo(dto.getInfo());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getType() != null) {
            product.setType(dto.getType());
        }
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setId(dto.getSupplierId());
            product.setSupplier(supplier);
        }
        return product;
    }
}
