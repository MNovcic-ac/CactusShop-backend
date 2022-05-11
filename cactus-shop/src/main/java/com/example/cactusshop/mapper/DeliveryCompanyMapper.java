package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.company.CompanyResponseDto;
import com.example.cactusshop.dto.company.CreateCompanyDto;
import com.example.cactusshop.entity.DeliveryCompany;
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
public abstract class DeliveryCompanyMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "companyName")
    @Mapping(target = "comapnyContact")
    public abstract DeliveryCompany mapToEntity(CreateCompanyDto createCompanyDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "companyName")
    @Mapping(target = "comapnyContact")
    public abstract CompanyResponseDto mapToResponse(DeliveryCompany deliveryCompany);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<CompanyResponseDto> mapToResponse(List<DeliveryCompany> deliveryCompanies);
}
