package com.ellenmateus.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOSaleItem {

	private Integer productId;
	private Integer quantity;
	private Double price;
	private Integer saleId;

}