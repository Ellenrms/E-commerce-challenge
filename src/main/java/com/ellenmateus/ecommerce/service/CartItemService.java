package com.ellenmateus.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOCartItem;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.CartItem;
import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.repository.CartItemRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductService productService;
    

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(Integer id) {
        return cartItemRepository.findById(id);
    }

    public CartItem createCartItem(DTOCartItem dto) {
        Product product = productService.getProductById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + dto.getId()));
        
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        return cartItemRepository.save(cartItem);
    }
    
    public CartItem updateCartItem(Integer id, DTOCartItem dto) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with ID: " + id));
        
        Product product = productService.getProductById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + dto.getId()));
        
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        
        return cartItemRepository.save(cartItem);
    }
    


    public void deleteCartItem(Integer id) {
        cartItemRepository.deleteById(id);
    }


}
