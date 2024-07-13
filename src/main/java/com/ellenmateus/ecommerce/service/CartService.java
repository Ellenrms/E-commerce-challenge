package com.ellenmateus.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOCart;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.Cart;
import com.ellenmateus.ecommerce.model.CartItem;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CartItemService cartItemService;
    
    
    

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    

    public Optional<Cart> getCartById(Integer id) {
        return cartRepository.findById(id);
    }

    
    
    public Cart createCart(DTOCart dto) {
        User user = userService.getUserById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getId()));
        			Cart cart = new Cart();
        			cart.setUser(user);
        			return cartRepository.save(cart);
    }
    
    
    public Cart updateCart(Integer id, DTOCart dto) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id));
        User user = userService.getUserById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getId()));
             cart.setUser(user);
             return cartRepository.save(cart);
    }
    

    public void deleteCart(Integer id) {
        cartRepository.deleteById(id);
    }

    
    public Cart getActiveCart(Integer userId) {
        return cartRepository.findByUserIdAndStatus(userId, Cart.CartStatus.ACTIVE);
    }
 
    
    public Cart addItemToCart(Integer userId, CartItem cartItem) {
        	Cart cart = getActiveCart(userId);
        		if (cart == null) {
        	cart = new Cart();
            User user = userService.getUserById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            cart.setUser(user);
            cart.setStatus(Cart.CartStatus.ACTIVE);
        }

        		cart.getItems().add(cartItem);
        		cartItem.setCart(cart);
        		return cartRepository.save(cart);
    }

    public void removeItemFromCart(Integer cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

    
    
    public void finalizeCart(Integer userId) {
        	Cart cart = getActiveCart(userId);
        		if (cart != null) {
            cart.setStatus(Cart.CartStatus.FINALIZED);
            cartRepository.save(cart);
        }
    }
}

