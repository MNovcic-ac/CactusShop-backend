package com.example.cactusshop.dto.product;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    @NotNull
    private String productName;

    @NotNull
    private String productDescription;

    @NotNull
    private Long price;

    @NotNull
    private Long lifeExpectancy;

    @NotNull
    private String supplierId;

    @NotNull
    private String productTypeId;

    @NotNull
    private Integer stock;
}
