package com.fooddelivery.food_delivery_app.service;

import com.fooddelivery.food_delivery_app.dto.UserRegistrationRequest;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String registerUser(UserRegistrationRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already Exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        //Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered sucessfully";

    }
}
