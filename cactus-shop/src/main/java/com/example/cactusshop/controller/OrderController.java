package com.example.cactusshop.controller;

import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.order.OrderResponseDto;
import com.example.cactusshop.dto.order.UpdateOrderDto;
import com.example.cactusshop.mapper.OrderMapper;
import com.example.cactusshop.service.OrderService;
import java.util.List;
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
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderResponseDto create(@RequestBody CreateOrderDto createOrderDto){
        return orderMapper.mapToResponse(orderService.create(createOrderDto));
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(){
        return orderMapper.mapToResponse(orderService.getOrders());
    }

    @GetMapping("/{uuid}")
    public OrderResponseDto getOrder(@PathVariable String uuid){
        return orderMapper.mapToResponse(orderService.getOrder(uuid));
    }

    @PutMapping("/{uuid}")
    public OrderResponseDto update(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable String uuid){
        return orderMapper.mapToResponse(orderService.update(updateOrderDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        orderService.delete(uuid);
    }
}
