package com.ellenmateus.ecommerce.repository;

import com.ellenmateus.ecommerce.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {
}