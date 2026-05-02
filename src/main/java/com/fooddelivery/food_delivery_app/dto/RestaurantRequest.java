package com.fooddelivery.food_delivery_app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RestaurantRequest {
    @NotBlank(message = "restaurant name is required")
    private String restaurantName;
    @Pattern(regexp = "[0-9]{10}$", message = "phone number should be 10 digit number")
    private String phone;
    private boolean active;
    @NotBlank(message= "address is required")
    private String address;
}
