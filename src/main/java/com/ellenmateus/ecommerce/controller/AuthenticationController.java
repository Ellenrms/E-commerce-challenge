package com.ellenmateus.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.dto.AuthResponse;
import com.ellenmateus.ecommerce.dto.DTOAuthentication;
import com.ellenmateus.ecommerce.dto.DTOLoginResponse;
import com.ellenmateus.ecommerce.dto.DTORegister;
import com.ellenmateus.ecommerce.dto.LoginRequest;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.UserRepository;
import com.ellenmateus.ecommerce.security.TokenService;
import com.ellenmateus.ecommerce.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid DTOAuthentication data) {
        AuthResponse response = authService.login(new LoginRequest(data.getEmail(), data.getPassword()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid DTORegister data) {
        if (this.repository.findByEmail(data.getEmail()).isPresent())
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(null, data.getName(), data.getEmail(), encryptedPassword, data.getRole(), null, null, null, null, null);

        this.repository.save(newUser);

        var token = tokenService.generateToken(newUser);

        return ResponseEntity.ok(new DTOLoginResponse(token));
    }
}
