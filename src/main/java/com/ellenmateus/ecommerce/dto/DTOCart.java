package com.ellenmateus.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
	public class DTOCart {
	
	    private Integer id;
	    private DTOUser user;
	    private List<DTOCartItem> items;
	    private LocalDateTime creationDate;
	    private LocalDateTime updateDate;


}
