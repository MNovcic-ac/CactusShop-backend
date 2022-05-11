package com.example.cactusshop.dto.user;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String contactEmail;

    @NotNull
    private String contactPhone;

    @NotNull
    private String password;

}
