package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.supplier.CreateSupplierDto;
import com.example.cactusshop.dto.supplier.SupplierResponseDto;
import com.example.cactusshop.entity.Supplier;
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
public abstract class SupplierMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "supplierName")
    @Mapping(target = "supplierContactEmail")
    @Mapping(target = "supplierContactPhone")
    public abstract Supplier mapToEntity(CreateSupplierDto createSupplierDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "supplierName")
    @Mapping(target = "supplierContactPhone")
    public abstract SupplierResponseDto mapToResponse(Supplier supplier);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<SupplierResponseDto> mapToResponse(List<Supplier> suppliers);
}
