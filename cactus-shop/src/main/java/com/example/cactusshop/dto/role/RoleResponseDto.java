package com.example.cactusshop.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {

    private String roleName;

    private String roleDescription;
}
