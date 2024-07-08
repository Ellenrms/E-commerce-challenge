package com.ellenmateus.ecommerce.service;


import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	 @Autowired
	    private ProductRepository productRepository;

	    public List<Product> findAll() {
	        return productRepository.findAll();
	    }

	    public Optional<Product> findById(Long id) {
	        return productRepository.findById(id);
	    }

	    public Product save(Product product) {
	        return productRepository.save(product);
	    }

	    public void deleteById(Long id) {
	        productRepository.deleteById(id);
	    }

	    public void deactivateProduct(Long id) {
	        Optional<Product> product = findById(id);
	        if (product.isPresent()) {
	            Product updatedProduct = product.get();
	            updatedProduct.setActive(false);
	            save(updatedProduct);
	        }
	    }

	    public boolean canDeleteProduct(Long productId) {
	        // Verificar se o produto está incluído em alguma venda.
	        // Retornar falso se o produto estiver incluído em alguma venda.
	    	
	        return true; // Atualizar conforme a lógica específica.
	    }

	    public boolean isStockAvailable(Long productId, Integer quantity) {
	        Optional<Product> product = findById(productId);
	        return product.isPresent() && product.get().getStock() >= quantity;
	    }
	}