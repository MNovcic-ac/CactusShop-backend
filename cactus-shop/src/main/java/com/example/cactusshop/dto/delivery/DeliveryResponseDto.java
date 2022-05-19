package com.example.cactusshop.dto.delivery;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class DeliveryResponseDto {

    private OffsetDateTime deliveredAt;

    private String addressId;

    private String deliveryCompanyId;
}
