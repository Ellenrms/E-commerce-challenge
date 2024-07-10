package com.ellenmateus.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ellenmateus.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	
	@Query("SELECT COUNT(si) > 0 FROM SaleItem si WHERE si.product.id = :productId")
    boolean isProductInAnySale(@Param("productId") Integer productId);
}