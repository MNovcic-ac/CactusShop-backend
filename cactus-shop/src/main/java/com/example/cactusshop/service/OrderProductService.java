package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.order.CreateOrderDto;
import com.example.cactusshop.dto.orderproduct.CreateOrderProductDto;
import com.example.cactusshop.dto.orderproduct.UpdateOrderProductDto;
import com.example.cactusshop.entity.Order;
import com.example.cactusshop.entity.OrderProduct;
import com.example.cactusshop.entity.Product;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.OrderProductMapper;
import com.example.cactusshop.repository.OrderProductRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final OrderProductMapper orderProductMapper;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderProduct create(CreateOrderProductDto createOrderProductDto){
        Product foundProduct = productService.getById(createOrderProductDto.getProductId());

        try {
            orderService.getOrder(createOrderProductDto.getOrderId());
        }catch(NotFoundException e){
            Long amount = foundProduct.getPrice() * createOrderProductDto.getQuantity();

            CreateOrderDto createOrderDto = new CreateOrderDto(
                    amount,
                    0L,
                    createOrderProductDto.getQuantity(),
                    false,
                    createOrderProductDto.getUserId());

            Order createdOrder = orderService.create(createOrderDto);

            OrderProduct orderProduct = orderProductMapper.mapToEntity(createOrderProductDto);
            orderProduct.setProduct(foundProduct);
            orderProduct.setOrder(createdOrder);

            return orderProductRepository.save(orderProduct);
        }

        Order foundOrder = orderService.getOrder(createOrderProductDto.getOrderId());
        OrderProduct orderProduct = orderProductMapper.mapToEntity(createOrderProductDto);
        orderProduct.setProduct(foundProduct);
        orderProduct.setOrder(foundOrder);

        return orderProductRepository.save(orderProduct);
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
