package com.fooddelivery.food_delivery_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}

