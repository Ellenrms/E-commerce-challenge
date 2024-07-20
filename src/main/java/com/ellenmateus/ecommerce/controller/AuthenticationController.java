package com.ellenmateus.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.dto.DTOAuthentication;
import com.ellenmateus.ecommerce.dto.DTOLoginResponse;
import com.ellenmateus.ecommerce.dto.DTORegister;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.UserRepository;
import com.ellenmateus.ecommerce.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid DTOAuthentication data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		

		return ResponseEntity.ok(new DTOLoginResponse(token));

	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid DTORegister data) {
		
		System.out.println("teste1");
		System.out.println("esta é minha informação: " + data);
		
		//if (this.repository.findByEmail(data.getEmail())!= null) return ResponseEntity.badRequest().build();		
		
			

		
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		 User newUser = new User(null, data.getName(), data.getEmail(), encryptedPassword, data.getRole(), null, null, null, null, null);
		 
		this.repository.save(newUser);
		
		var token = tokenService.generateToken(newUser);
		System.out.println(newUser);
		
		System.out.println(token);

		return ResponseEntity.ok().build();
	}

}
