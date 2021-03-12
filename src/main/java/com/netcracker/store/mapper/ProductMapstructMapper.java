package com.netcracker.store.mapper;

import com.netcracker.store.dto.ProductBasketDto;
import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.ProductPostDto;
import com.netcracker.store.dto.ProductPutDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProductMapstructMapper {

    ProductMapstructMapper PRODUCT_MAPSTRUCT_MAPPER= Mappers.getMapper(ProductMapstructMapper.class);

    @Mapping(source = "product.supplier.id" ,target = "supplierId")
    @Named("toProductDto")
    ProductDto mapToProductDto(Product product);


    @Mapping(source = "newCount",target = "count")
    @Mapping(source = "productDto.supplierId",target = "supplierId")
    ProductBasketDto toProductBasketDto(ProductDto productDto, int newCount);

    @Mapping(source = "productPostDto.supplierId",target = "supplier.id")
    Product mapToProduct(ProductPostDto productPostDto);

    @Mapping(source = "productPutDto.supplierId" , target = "supplier.id")
    Product mapToProduct(ProductPutDto productPutDto);

    default List<ProductDto> toProductDtoList(List<Product> productList) {
        List<ProductDto> result = new ArrayList<>();
        if (productList == null) {
            return result;
        }
        productList.forEach(product -> result.add(mapToProductDto(product)));
        return result;
    }

    default Product patch(Product product, ProductDto dto) {
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
