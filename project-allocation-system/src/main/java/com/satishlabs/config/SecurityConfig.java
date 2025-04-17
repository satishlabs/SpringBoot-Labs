package com.satishlabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // Disable CSRF for API requests
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/allocations/**").authenticated()  // Require authentication
                        .anyExchange().permitAll()  // Allow other requests
                )
                .httpBasic(withDefaults())  // Enable Basic Authentication
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Encode password only once, not every time the method is called
        String encodedPassword = passwordEncoder.encode("password");

        System.out.println("ENCODED PASSWORD: " + encodedPassword); // Debugging

        UserDetails admin = User.withUsername("admin")
                .password(encodedPassword)  // Store encoded password
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password(encodedPassword)
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(admin, user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


