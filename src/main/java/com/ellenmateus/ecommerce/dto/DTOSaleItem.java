package com.ellenmateus.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor	
	public class DTOSaleItem {
	    private Integer id;
	    private Integer productId;
	    private Integer quantity;
	    private Double price;

	    public DTOSaleItem(Integer id, Integer productId, Integer quantity, Double price) {
	        this.id = id;
	        this.productId = productId;
	        this.quantity = quantity;
	        this.price = price;
	    }
	}



