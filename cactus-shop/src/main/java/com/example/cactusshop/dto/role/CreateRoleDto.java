package com.example.cactusshop.dto.role;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {

    @NotNull
    private String roleName;

    @NotNull
    private String roleDescription;
}
