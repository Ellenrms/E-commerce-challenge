package com.ellenmateus.ecommerce.repository;

import com.ellenmateus.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findByUserIdAndStatus(Integer userId, Cart.CartStatus status);
}