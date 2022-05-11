package com.example.cactusshop.dto.type;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductTypeDto {

    @NotNull
    private String typeName;
}
