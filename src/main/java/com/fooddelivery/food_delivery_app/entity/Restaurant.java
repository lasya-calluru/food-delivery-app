package com.fooddelivery.food_delivery_app.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String restaurantName;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private boolean isActive;
    @Column(nullable = false)
    private String address;
    @ManyToOne
    @JoinColumn(name ="owner_id")
    private User owner;

}
