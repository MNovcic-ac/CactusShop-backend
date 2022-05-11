package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.delivery.CreateDeliveryDto;
import com.example.cactusshop.dto.delivery.DeliveryResponseDto;
import com.example.cactusshop.entity.Delivery;
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
public abstract class DeliveryMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "deliveredAt")
    @Mapping(target = "createdAt")
    public abstract Delivery mapToEntity(CreateDeliveryDto createDeliverDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "deliveredAt")
    @Mapping(target = "addressId", source = "delivery.address.uuid")
    @Mapping(target = "deliveryCompanyId", source = "delivery.deliveryCompany.uuid")
    public abstract DeliveryResponseDto mapToResponse(Delivery delivery);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<DeliveryResponseDto> mapToResponse(List<Delivery> deliveries);
}
