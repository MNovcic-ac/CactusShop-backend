package com.example.cactusshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String uuid;

    private String fullName;

    private String contactPhone;

    private String contactEmail;
}
