package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.entity.Order;
import com.example.cactusshop.entity.OrderProduct;
import com.example.cactusshop.entity.Product;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.OrderProductMapper;
import com.example.cactusshop.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public void create(OrderProduct orderProduct){
        orderProductRepository.save(orderProduct);
    }

    public OrderProduct getById(String uuid){
         return orderProductRepository.findById(uuid).orElseThrow(()->{
             throw new NotFoundException(ErrorCodes.ORDER_PRODUCT_NOT_FOUND);
         });
    }

    public void delete(String uuid){
        orderProductRepository.delete(getById(uuid));
    }

}
