package com.fooddelivery.food_delivery_app.controller;

import com.fooddelivery.food_delivery_app.dto.UserRegistrationRequest;
import com.fooddelivery.food_delivery_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationRequest request){
        String response = userService.registerUser(request);
        return ResponseEntity.ok(response);

        }
    }
/*
@RestController — this class handles incoming API requests
@RequestMapping("/api/auth") — all APIs in this controller start with /api/auth
@PostMapping("/register") — this specific API is a POST request at /api/auth/register
@Valid — triggers the validations we wrote in DTO like @NotBlank, @Email
@RequestBody — converts the incoming JSON request into our UserRegistrationRequest object
ResponseEntity.ok() — sends back HTTP 200 with our success message
 */

