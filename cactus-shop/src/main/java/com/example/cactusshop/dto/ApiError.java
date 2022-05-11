package com.example.cactusshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  private String message;

  private String explanation;

  private String code;
}
