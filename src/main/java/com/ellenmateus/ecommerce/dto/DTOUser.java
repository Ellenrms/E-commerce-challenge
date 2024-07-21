package com.ellenmateus.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOUser {
	 private Integer id;
	    private String name;
	    private String email;
	    private String password;
	    private String city;
	    private String role;

  
}
