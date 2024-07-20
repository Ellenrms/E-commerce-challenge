package com.ellenmateus.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.dto.DTOPasswordResetRequest;
import com.ellenmateus.ecommerce.dto.DTOPasswordResetToken;
import com.ellenmateus.ecommerce.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid DTOPasswordResetRequest request) {
        userService.initiatePasswordReset(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm-reset")
    public ResponseEntity<Void> confirmReset(@RequestBody @Valid DTOPasswordResetToken DTOtoken) {
        userService.resetPassword(DTOtoken.getToken(), DTOtoken.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
