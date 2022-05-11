package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.type.CreateProductTypeDto;
import com.example.cactusshop.dto.type.ProductTypeResponseDto;
import com.example.cactusshop.entity.ProductType;
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
public abstract class ProductTypeMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "typeName")
    public abstract ProductType mapToEntity(CreateProductTypeDto createProductTypeDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "typeName")
    public abstract ProductTypeResponseDto mapToResponse(ProductType productType);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<ProductTypeResponseDto> mapToResponse(List<ProductType> types);
}
