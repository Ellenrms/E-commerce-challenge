package com.ellenmateus.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class DTOSale {
		private Integer id;
	    private Integer userId;
	    private Integer addressId;
	    private List<DTOSaleItem> items;
	    private Integer paymentId;
	    private Double totalAmount;
	    private LocalDateTime saleDate;
	    
	    public Double getTotalAmount() {
	        return totalAmount;
	    }

	}


