package com.example.cactusshop.dto.company;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDto {

    @NotNull
    private String companyName;

    @NotNull
    private String companyContact;
}
