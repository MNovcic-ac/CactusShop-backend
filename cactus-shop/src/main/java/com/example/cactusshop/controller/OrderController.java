package com.example.cactusshop.controller;

import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.order.OrderResponseDto;
import com.example.cactusshop.dto.order.UpdateOrderDto;
import com.example.cactusshop.mapper.OrderMapper;
import com.example.cactusshop.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cactusshop.security.SecurityService.validateAdmin;
import static com.example.cactusshop.security.SecurityService.validateConcreteUser;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/stripe-invoice")
    public OrderResponseDto create(@RequestBody String body){
        return orderMapper.mapToResponse(orderService.create(new JSONObject(body)));
    }

    @GetMapping("/all")
    public List<OrderResponseDto> getOrders(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestHeader(name = "Authorization") String token){
        validateAdmin(token);
        return orderMapper.mapToResponse(orderService.getOrders(page));
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(
            @RequestParam(name = "user", required = false) String userId,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestHeader(name = "Authorization") String token){
        validateConcreteUser(token, userId);
        return orderMapper.mapToResponse(orderService.getOrders(userId, page));
    }

    @GetMapping("/{uuid}")
    public OrderResponseDto getOrder(@PathVariable String uuid, @RequestHeader(name = "Authorization") String token){
        validateAdmin(token);
        return orderMapper.mapToResponse(orderService.getOrder(uuid));
    }

    @PutMapping("/{uuid}")
    public OrderResponseDto update(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable String uuid, @RequestHeader(name = "Authorization") String token){
        validateAdmin(token);
        return orderMapper.mapToResponse(orderService.update(updateOrderDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid, @RequestHeader(name = "Authorization") String token){
        validateAdmin(token);
        orderService.delete(uuid);
    }
}
