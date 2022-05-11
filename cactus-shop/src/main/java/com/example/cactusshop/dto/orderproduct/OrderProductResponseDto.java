package com.example.cactusshop.dto.orderproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponseDto {

    private String orderId;

    private String productId;

    private Integer quantity;
}
