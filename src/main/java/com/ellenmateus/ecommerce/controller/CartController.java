package com.ellenmateus.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.dto.DTOCart;
import com.ellenmateus.ecommerce.model.Cart;
import com.ellenmateus.ecommerce.model.CartItem;
import com.ellenmateus.ecommerce.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Carts", description = "Endpoints for managing shopping carts")
public class CartController {

    @Autowired
    private CartService cartService;
    
    
    
    @GetMapping("/active")
    public Cart getActiveCart(@RequestParam Integer userId) {
        return cartService.getActiveCart(userId);
    }

    
    @PostMapping("/add")
    public Cart addItemToCart(@RequestParam Integer userId, @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(userId, cartItem);
    }
    
    
    @DeleteMapping("/remove/{itemId}")
    public void removeItemFromCart(@PathVariable Integer itemId) {
        cartService.removeItemFromCart(itemId);
    }
    
    
    @PostMapping("/finalize")
    public void finalizeCart(@RequestParam Integer userId) {
        cartService.finalizeCart(userId);
    }
    
    
    @GetMapping
    @Operation(summary = "Get all carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by ID")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        Optional<Cart> cart = cartService.getCartById(id);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    @PostMapping
    @Operation(summary = "Create a new cart")
    public Cart createCart(@RequestBody DTOCart cart) {
        return cartService.createCart(cart);
    }

    
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart by ID")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        Optional<Cart> cart = cartService.getCartById(id);
        		if (cart.isPresent()) {
        			cartService.deleteCart(id);
        		return ResponseEntity.ok().build();
        	} else {
        		return ResponseEntity.notFound().build();
        }
    }
}
