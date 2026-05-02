package com.fooddelivery.food_delivery_app.controller;


import com.fooddelivery.food_delivery_app.dto.RestaurantRequest;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.service.RestuarantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    RestuarantService restuarantService;

    @PostMapping("/addRestaurant")
    @PreAuthorize("hasRole('RESTAURANT_OWNER')")
    public ResponseEntity<String> addRestaurant(@Valid @RequestBody RestaurantRequest request){
        User loggedInUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String response = restuarantService.addRestuarant(request,loggedInUser);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('RESTAURANT_OWNER')")
    public List<Restaurant> getAllRestaurants(){

        List response = restuarantService.getAllRestaurants();
        return response;
    }
    @GetMapping("/my")
    @PreAuthorize("hasRole('RESTAURANT_OWNER')")
    public List<Restaurant> getMyRestaurants(){
        User loggedInUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        List response = restuarantService.getMyRestaurants(loggedInUser);
        return response;


    }

}
