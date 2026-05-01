package com.fooddelivery.food_delivery_app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                );

                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
/*
Understand what this does:
@Configuration — tells Spring this is a configuration class
@EnableWebSecurity — enables Spring Security
.csrf(csrf -> csrf.disable()) — disables CSRF for now so we can test with Postman
.requestMatchers("/api/auth/**").permitAll() — allows register and login APIs without authentication
.anyRequest().authenticated() — all other APIs need a valid token
BCryptPasswordEncoder — this is the password encryption bean that UserService uses
 */
