package com.netcracker.store.mapper;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.entity.Product;
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
    @Mapping(source = "product.supplier.id",target = "supplierId")
    ProductDto toProductBasketDto(Product product, int newCount);


    @Mapping(source = "productPostDto.supplierId",target = "supplier.id")
    Product mapToProductPost(ProductDto productPostDto);


    @Mapping(source = "productPutDto.supplierId",target = "supplier.id")
    Product mapToProductPut(ProductDto productPutDto);

    default List<ProductDto> toProductDtoList(List<Product> productList) {
        List<ProductDto> result = new ArrayList<>();
        if (productList == null) {
            return result;
        }
        productList.forEach(product -> result.add(mapToProductDto(product)));
        return result;
    }
}
