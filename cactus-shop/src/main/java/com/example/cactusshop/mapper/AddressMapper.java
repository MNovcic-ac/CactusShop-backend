package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.address.AddressResponseDto;
import com.example.cactusshop.dto.address.CreateAddressDto;
import com.example.cactusshop.entity.Address;
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
public abstract class AddressMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "streetName")
    @Mapping(target = "streetNumber")
    @Mapping(target = "town")
    @Mapping(target = "postalCode")
    public abstract Address mapToEntity(CreateAddressDto createAddressDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "fullAddress", expression = "java(address.getStreetName() + \" \" + address.getStreetNumber() + \",\" + address.getTown() + \" \" + address.getPostalCode())")
    public abstract AddressResponseDto mapToResponse(Address address);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<AddressResponseDto> mapToResponse(List<Address> addresses);
}
