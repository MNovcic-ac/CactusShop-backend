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

    private Integer totalNumberOfItems;

    @NotNull
    private String userId;
}
