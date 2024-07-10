package com.ellenmateus.ecommerce.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellenmateus.ecommerce.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    boolean existsByItems_ProductId(Integer productId);
}	