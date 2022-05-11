package com.example.cactusshop.mapper;

import com.example.cactusshop.dto.orderproduct.CreateOrderProductDto;
import com.example.cactusshop.dto.orderproduct.OrderProductResponseDto;
import com.example.cactusshop.entity.OrderProduct;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class OrderProductMapper {

    @Named("mapToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "quantity")
    public abstract OrderProduct mapToEntity(CreateOrderProductDto createOrderProductDto);

    @Named("mapToResponse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "quantity")
    @Mapping(target = "orderId", source = "orderProduct.order.uuid")
    @Mapping(target = "productId", source = "orderProduct.product.uuid")
    public abstract OrderProductResponseDto mapToResponse(OrderProduct orderProduct);
}
