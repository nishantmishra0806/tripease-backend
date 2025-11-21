package com.tripease.security;

import com.tripease.services.CombinedUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CombinedUserDetailsService uds;

    public SecurityConfig(CombinedUserDetailsService uds) {
        this.uds = uds;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-ui/index.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/v2/api-docs",
                                "/swagger.json",
                                "/swagger/**"
                        ).permitAll()

                        // Public auth endpoints
                        .requestMatchers("/auth/**").permitAll()

                        // Role-based endpoint
                        .requestMatchers("/driver/update/**", "/cab/**" , "/api/locations/update").hasRole("DRIVER")
                        .requestMatchers("/booking/**" , "/customer/**").hasRole("CUSTOMER")

                        // everything else -> authenticated
                        .anyRequest().authenticated()
                )
                .userDetailsService(uds)
                .formLogin(form -> form.permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}