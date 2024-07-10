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
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.CartItem;
import com.ellenmateus.ecommerce.service.CartItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cartitems")
@Tag(name = "CartItems", description = "Endpoints for managing items within a shopping cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "Get all cart items")
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart item by ID")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        if (cartItem.isPresent()) {
            return ResponseEntity.ok(cartItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create an item to a cart")
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart item by ID")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        if (cartItem.isPresent()) {
            cartItemService.deleteCartItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}