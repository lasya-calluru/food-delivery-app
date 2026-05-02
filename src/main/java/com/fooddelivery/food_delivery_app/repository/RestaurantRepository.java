package com.fooddelivery.food_delivery_app.repository;

import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findByOwner(User owner);
}
