package com.fooddelivery.food_delivery_app.config;

import com.fooddelivery.food_delivery_app.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1 - Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // Step 2 - Check if header exists and starts with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3 - Extract token (remove "Bearer " prefix)
        String token = authHeader.substring(7);

        // Step 4 - Validate token
        if (!jwtUtil.validateToken(token)) {
            filterChain.doFilter(request, response);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Step 5 - Extract email from token
        String email = jwtUtil.extractEmail(token);

        // Step 6 - Load user from database
        userRepository.findByEmail(email).ifPresent(user -> {
            // Step 7 - Set user in Security Context
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });

        // Step 8 - Continue to next filter
        filterChain.doFilter(request, response);
    }
}