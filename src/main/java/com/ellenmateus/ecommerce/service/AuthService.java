package com.ellenmateus.ecommerce.service;

import com.ellenmateus.ecommerce.dto.AuthResponse;
import com.ellenmateus.ecommerce.dto.LoginRequest;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.UserRepository;
import com.ellenmateus.ecommerce.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            String token = tokenService.generateToken(user);
            return new AuthResponse(token);
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
