package com.example.cactusshop.dto.delivery;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryDto {

    @NotNull
    private OffsetDateTime deliveredAt;

    @NotNull
    private OffsetDateTime createdAt;

    @NotNull
    private String addressId;

    @NotNull
    private String companyId;
}
