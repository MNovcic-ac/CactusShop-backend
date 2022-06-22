package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.order.UpdateOrderDto;
import com.example.cactusshop.dto.orderproduct.CreateOrderProductDto;
import com.example.cactusshop.entity.Order;
import com.example.cactusshop.entity.OrderProduct;
import com.example.cactusshop.entity.User;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.OrderMapper;
import com.example.cactusshop.repository.OrderRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final ProductService productService;
    private final OrderProductService orderProductService;

    public List<Order> getOrders(Integer page){
        return orderRepository.findAll(PageRequest.of(page-1, 20)).getContent();
    }

    public List<Order> getOrders(String userId, Integer page) {
        User user = userService.getUserById(userId);

        return orderRepository.getForUser(user, PageRequest.of((page - 1), 20)).getContent();
    }

    public Order getOrder(String uuid) {
        return orderRepository.findById(uuid).orElseThrow(() -> {
            throw new NotFoundException(ErrorCodes.ORDER_NOT_FOUND_1);
        });
    }

    private Order getOrder(String uuid, String errorCode) {
        return orderRepository.findById(uuid).orElseThrow(() -> {
            throw new NotFoundException(errorCode);
        });
    }

    public Order create(CreateOrderDto createOrderDto) {
        User foundUser = userService.getUserById(createOrderDto.getUserId());
        Order created = orderMapper.mapToEntity(createOrderDto);
        created.setUser(foundUser);

        log.info("Successfully created order {}", created);
        return orderRepository.save(created);
    }

    public Order update(UpdateOrderDto updateOrderDto, String uuid) {
        Order foundOrder = getOrder(uuid, ErrorCodes.ORDER_NOT_FOUND_2);

        foundOrder = buildUpdatedOrder(foundOrder, updateOrderDto);

        return orderRepository.save(foundOrder);
    }

    private Order buildUpdatedOrder(Order foundOrder, UpdateOrderDto updateOrderDto) {
        User foundUser = userService.getUserById(updateOrderDto.getUserId());
        foundOrder.setAmount(updateOrderDto.getAmount());
        foundOrder.setUser(foundUser);

        return foundOrder;
    }

    public void delete(String uuid) {
        Order foundOrder = getOrder(uuid, ErrorCodes.ORDER_NOT_FOUND_3);

        orderRepository.delete(foundOrder);
    }

    public Order create(JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        String amount = data.getJSONObject("object").get("amount").toString();
        String id = data.getJSONObject("object").get("id").toString();
        String description = data.getJSONObject("object").get("description").toString();

        String[] descriptionParts = description.split(" ------------------");

        User user = userService.findByEmail(descriptionParts[0]);

        String[] items = descriptionParts[1].split("\"");

        Order create = new Order();
        create.setAmount(Long.valueOf(amount));
        create.setUser(user);
        create.setStripeId(id);

        create = orderRepository.save(create);

        int i = 3;
        while(i < items.length){
            String itemAmount = items[i+7];
            String itemId = items[i];
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(create);
            orderProduct.setProduct(productService.getById(itemId));
            orderProduct.setQuantity(Integer.valueOf(itemAmount.substring(1, itemAmount.length()-1)));

            orderProductService.create(orderProduct);

            log.info("Created OrderProduct: %s", orderProduct);
            i += 16;
        }

        return create;
    }

    public Order getByStripeId(String stripeId){
        return orderRepository.findByStripeId(stripeId);
    }
}
