package com.example.cactusshop.dto.address;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDto {

    @NotNull
    private String streetName;

    @NotNull
    private String streetNumber;

    @NotNull
    private String town;

    @NotNull
    private String postalCode;
}
