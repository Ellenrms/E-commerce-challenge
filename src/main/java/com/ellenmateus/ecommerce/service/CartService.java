package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOCart;
import com.ellenmateus.ecommerce.dto.DTONewCartItem;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.Cart;
import com.ellenmateus.ecommerce.model.CartItem;
import com.ellenmateus.ecommerce.model.Product;
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

	@Autowired
	private ProductService productService;

	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	public Optional<Cart> getCartById(Integer id) {
		return cartRepository.findById(id);
	}

	public Cart findById(Integer id) {
		Optional<Cart> obj = cartRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
	}

	public Cart createCart(Integer userId) {
		User user = userService.getUserById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	public Cart updateCart(Integer id, DTOCart dto) {
		Cart cart = cartRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + id));
		User user = userService.getUserById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getId()));
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	public void deleteCart(Integer id) {
		cartRepository.deleteById(id);
	}

	public Cart getActiveCart(Integer userId) {
		return cartRepository.findByUserIdAndStatus(userId, Cart.CartStatus.ACTIVE);
	}

	public Cart addItemToCart(DTONewCartItem dtoNewCartItem) {
		Cart cart = findById(dtoNewCartItem.cartId());

		// procurar o produto
		Product product = productService.getProductById(dtoNewCartItem.productId()).orElseThrow(
				() -> new ResourceNotFoundException("Product not found with ID: " + dtoNewCartItem.productId()));

		// chamar metodo para criar um itemCart createSaleItem2
		CartItem cartItem = cartItemService.createCartItem2(product, cart, dtoNewCartItem.quantity());

		// inserir o item ao carrinho
		cart.getItems().add(cartItem);

		return cartRepository.save(cart);
	}

	public void removeItemFromCart(Integer cartItemId) {

		CartItem cartItem = cartItemService.getCartItemById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem not found with ID: " + cartItemId));

		Cart cart = getCartById(cartItem.getCart().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Cart not found with ID: " + cartItem.getCart().getId()));

		cartItemService.deleteCartItem(cartItemId);
		cartRepository.save(cart);
	}

	public void finalizeCart(Integer cartId) {
		Cart cart = getCartById(cartId)
		.orElseThrow(() -> new ResourceNotFoundException("Cart not found with ID: " + cartId));
		if (cart != null) {
			cart.setStatus(Cart.CartStatus.FINALIZED);
			cartRepository.save(cart);
		}
	}
}
