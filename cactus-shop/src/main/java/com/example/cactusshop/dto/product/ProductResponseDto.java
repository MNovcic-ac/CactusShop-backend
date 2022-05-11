package com.example.cactusshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String productName;

    private Long price;

    private String supplier;

    private Integer stock;
}
