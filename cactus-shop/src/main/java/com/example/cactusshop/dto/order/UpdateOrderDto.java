package com.example.cactusshop.dto.order;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {

    private Long amount;

    private Long percentOff;

    private Integer totalNumberOfItems;

    private boolean overNightDelivery;

    @NotNull
    private String userId;
}
