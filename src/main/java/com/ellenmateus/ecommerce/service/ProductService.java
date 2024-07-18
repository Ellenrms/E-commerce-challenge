package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOProduct;
import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Service
@Tag(name = "Product Service", description = "Service responsible for the business logic of the products")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	@Lazy
	private SaleService saleService;
	

	
	
	private Product convertToEntity(DTOProduct dtoProduct) {
		Product product = new Product();
		product.setName(dtoProduct.getName());
		product.setDescription(dtoProduct.getDescription());
		product.setPrice(dtoProduct.getPrice());
		product.setActive(dtoProduct.isActive());
		product.setStock(dtoProduct.getStock());
		return product;
	}
	
	
	
		@Cacheable(value = "products")
		public List<Product> findAll() {
		return productRepository.findAll();
	}
		
		
		public Optional<Product> getProductById(Integer id) {
	        return productRepository.findById(id);
   }
	 
	
	
	@Operation(summary = "Save a new product")
	@CacheEvict(value = "products", allEntries = true)
	   public Product save(DTOProduct dtoProduct) {
		Product product = convertToEntity(dtoProduct);
		return productRepository.save(product);
		
    }
	 
	

	@Operation(summary = "Check if a product is on sale")
	public boolean isProductInAnySale(Integer productId) {
		return saleService.isProductInAnySale(productId);
	}
	
	

	@Operation(summary = "Deactivate a product")
	@CacheEvict(value = "products", allEntries = true)
	public void deactivateProduct(Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
		product.setActive(false);
		productRepository.save(product);
	}

	
	@Operation(summary = "Delete a product by ID")
	@CacheEvict(value = "products", allEntries = true)
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}
	

	@Operation(summary = "Search for a product by ID")
	@Cacheable(value = "products", key = "#id")
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}
	

	@Operation(summary = "Update an existing product")
	@CacheEvict(value = "products", allEntries = true)
	public Product updateProduct(Integer id, DTOProduct dtoProduct) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
		product = convertToEntity(dtoProduct);
		return productRepository.save(product);
		
	}
}
