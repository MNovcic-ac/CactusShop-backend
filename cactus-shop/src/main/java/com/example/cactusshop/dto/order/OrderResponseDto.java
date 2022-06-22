package com.example.cactusshop.dto.order;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private String userId;

    private Long amount;

    private Integer totalNumberOfItems;

    private OffsetDateTime orderedAt;
}
