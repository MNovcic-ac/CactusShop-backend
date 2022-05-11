package com.example.cactusshop.controller;

import com.example.cactusshop.dto.orderproduct.CreateOrderProductDto;
import com.example.cactusshop.dto.orderproduct.OrderProductResponseDto;
import com.example.cactusshop.dto.orderproduct.UpdateOrderProductDto;
import com.example.cactusshop.entity.OrderProduct;
import com.example.cactusshop.mapper.OrderProductMapper;
import com.example.cactusshop.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-products")
public class OrderProductController {

    private final OrderProductService orderProductService;
    private final OrderProductMapper orderProductMapper;

    @PostMapping
    public OrderProductResponseDto create(@RequestBody CreateOrderProductDto createOrderProductDto){
        return orderProductMapper.mapToResponse(orderProductService.create(createOrderProductDto));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        orderProductService.delete(uuid);
    }
}
