package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.order.OrderResponseDto;
import com.example.cactusshop.entity.Order;
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
public abstract class OrderMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "percentOff")
    @Mapping(target = "totalNumberOfItems", defaultValue = "0")
    @Mapping(target = "overNightDelivery", defaultValue = "false")
    public abstract Order mapToEntity(CreateOrderDto createOrderDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "userId", source = "order.user.uuid")
    @Mapping(target = "amount")
    @Mapping(target = "percentOff")
    @Mapping(target = "totalNumberOfItems")
    @Mapping(target = "overNightDelivery")
    @Mapping(target = "orderedAt")
    public abstract OrderResponseDto mapToResponse(Order order);

    @IterableMapping(qualifiedByName = "mapToResponse")
    public abstract List<OrderResponseDto> mapToResponse(List<Order> orders);
}
