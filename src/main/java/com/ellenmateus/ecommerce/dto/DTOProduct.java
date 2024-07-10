package com.ellenmateus.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOProduct {
	
	    private Integer id;
	    private String name;
	    private String description;
	    private double price;
	    private boolean active;
	    private Integer stock;

}
