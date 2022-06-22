package com.example.cactusshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String id;

    private String name;

    private Long price;

    private String supplier;

    private Integer stock;

    private Long lifeExpectancy;

    private String description;

    private String image;
}
