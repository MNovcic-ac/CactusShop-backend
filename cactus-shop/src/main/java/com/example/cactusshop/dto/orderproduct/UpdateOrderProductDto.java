package com.example.cactusshop.dto.orderproduct;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderProductDto{

    @NotNull
    private String orderId;

    @NotNull
    private String productId;

    @NotNull
    private Integer quantity;

    @NotNull
    private String userId;
}
