package com.fooddelivery.food_delivery_app.service;


import com.fooddelivery.food_delivery_app.config.JwtUtil;
import com.fooddelivery.food_delivery_app.dto.RestaurantRequest;
import com.fooddelivery.food_delivery_app.entity.Restaurant;
import com.fooddelivery.food_delivery_app.entity.User;
import com.fooddelivery.food_delivery_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RestuarantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    JwtUtil jwtUtil;
    public String addRestuarant(RestaurantRequest request, User loggedInUser){

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhone(request.getPhone());
        restaurant.setActive(true);
        restaurant.setOwner(loggedInUser);
        restaurantRepository.save(restaurant);

        return "Restaurant added sucessfully";


    }
    public List<Restaurant> getAllRestaurants()
        {
       return restaurantRepository.findAll();
    }
    public List<Restaurant> getMyRestaurants(User loggedInUser){
        return restaurantRepository.findByOwner(loggedInUser);
    }

}
