package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.order.UpdateOrderDto;
import com.example.cactusshop.entity.Order;
import com.example.cactusshop.entity.User;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.OrderMapper;
import com.example.cactusshop.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order getOrder(String uuid){
        return orderRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(ErrorCodes.ORDER_NOT_FOUND_1);
        });
    }

    private Order getOrder(String uuid, String errorCode){
        return orderRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(errorCode);
        });
    }

    public Order create(CreateOrderDto createOrderDto){
        User foundUser = userService.getUserById(createOrderDto.getUserId());
        Order created = orderMapper.mapToEntity(createOrderDto);
        created.setUser(foundUser);

        log.info("Successfully created order {}", created);
        return orderRepository.save(created);
    }

    public Order update(UpdateOrderDto updateOrderDto, String uuid){
        Order foundOrder = getOrder(uuid, ErrorCodes.ORDER_NOT_FOUND_2);

        foundOrder = buildUpdatedOrder(foundOrder, updateOrderDto);

        return orderRepository.save(foundOrder);
    }

    private Order buildUpdatedOrder(Order foundOrder, UpdateOrderDto updateOrderDto) {
        User foundUser = userService.getUserById(updateOrderDto.getUserId());
        foundOrder.setAmount(updateOrderDto.getAmount());
        foundOrder.setPercentOff(updateOrderDto.getPercentOff());
        foundOrder.setUser(foundUser);
        foundOrder.setOverNightDelivery(updateOrderDto.isOverNightDelivery());
        foundOrder.setTotalNumberOfItems(updateOrderDto.getTotalNumberOfItems());

        return foundOrder;
    }

    public void delete(String uuid){
        Order foundOrder = getOrder(uuid, ErrorCodes.ORDER_NOT_FOUND_3);

        orderRepository.delete(foundOrder);
    }
}
