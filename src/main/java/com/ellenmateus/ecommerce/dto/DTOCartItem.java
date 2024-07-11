package com.ellenmateus.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOCartItem {
    private Integer id;
    private DTOProduct product;
    private Integer quantity;
   
}