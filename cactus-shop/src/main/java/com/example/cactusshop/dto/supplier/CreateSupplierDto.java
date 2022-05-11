package com.example.cactusshop.dto.supplier;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplierDto {

    @NotNull
    private String supplierName;

    @NotNull
    private String supplierContactEmail;

    @NotNull
    private String supplierContactPhone;

    @NotNull
    private String addressId;
}
