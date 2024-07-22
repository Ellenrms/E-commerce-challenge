package com.ellenmateus.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/confirm-reset").permitAll()
                        .requestMatchers(HttpMethod.POST, "/email").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carts/finalize").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carts/create/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carts/add-item").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/sales").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/saleitems").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/sales/creatFromCart/{cartId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cartitems").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/exceptionlogs").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}/desactivate").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/sales/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/products").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/products/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/carts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/carts/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/carts/active").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sales/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sales").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cartitems").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cartitems/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sales/report/weekly").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sales/report/monthly").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/saleitems").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/saleitems/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/exceptionlogs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/exceptionlogs/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/carts/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/carts/remove/{itemId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/sales/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/products/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/cartitems/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/saleitems/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/exceptionlogs/{id}").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
