package com.example.PROJECT1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.PROJECT1.filters.JwtRequestFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration{
    // Autowiring the JwtRequestFilter for authentication
    @Autowired
    private JwtRequestFilter authFilter;
    // Configuring security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            // Disable CSRF protection
            .csrf(csrf -> csrf.disable())
            // Allowing certain endpoints to be accessed without authentication
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/authenticate", "/sign-up","/sign-up/confirm")
                .permitAll())
            // Securing API endpoints, requiring authentication
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/**")
                .authenticated())
            // Setting session management to stateless
            .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Adding JwtRequestFilter before UsernamePasswordAuthenticationFilter
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
    // Creating a bean for PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Creating a bean for AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
