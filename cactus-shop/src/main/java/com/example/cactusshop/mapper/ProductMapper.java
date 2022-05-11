package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.address.AddressResponseDto;
import com.example.cactusshop.dto.product.CreateProductDto;
import com.example.cactusshop.dto.product.ProductResponseDto;
import com.example.cactusshop.entity.Address;
import com.example.cactusshop.entity.Product;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class ProductMapper {

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "productName")
    @Mapping(target = "price")
    @Mapping(target = "supplier", source = "product.supplier.uuid")
    @Mapping(target = "stock")
    public abstract ProductResponseDto mapToResponse(Product product);

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "productName")
    @Mapping(target = "productDescription")
    @Mapping(target = "price")
    @Mapping(target = "lifeExpectancy")
    @Mapping(target = "stock")
    public abstract Product mapToEntity(CreateProductDto createProductDto);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<ProductResponseDto> mapToResponse(List<Product> products);
}
