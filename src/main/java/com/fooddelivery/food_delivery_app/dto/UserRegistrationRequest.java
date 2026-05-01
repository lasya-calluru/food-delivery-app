package com.fooddelivery.food_delivery_app.dto;

import com.fooddelivery.food_delivery_app.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "name is required")
    private String name;
    @Pattern(regexp = "[0-9]{10}$", message = "phone number should be 10 digit number")
    private String phone;

    private User.Role role;

}
