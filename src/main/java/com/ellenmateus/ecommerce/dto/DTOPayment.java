package com.ellenmateus.ecommerce.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPayment {
    private Integer id;
    private String paymentMethod;
    private Double amount;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;



}