package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOProduct;
import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Service
@Tag(name = "Product Service", description = "Service responsible for the business logic of the products")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SaleService saleService;

	private DTOProduct convertToDTO(Product product) {
		return new DTOProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
				product.getActive(), product.getStock());
	}

	private Product convertToEntity(DTOProduct dtoProduct) {
		Product product = new Product();
		product.setId(dtoProduct.getId());
		product.setName(dtoProduct.getName());
		product.setDescription(dtoProduct.getDescription());
		product.setPrice(dtoProduct.getPrice());
		product.setActive(dtoProduct.isActive());
		product.setStock(dtoProduct.getStock());
		return product;
	}

	@Operation(summary = "Save a new product")
	@CacheEvict(value = "products", allEntries = true)
	public DTOProduct save(DTOProduct dtoProduct) {
		Product product = convertToEntity(dtoProduct);
		Product savedProduct = productRepository.save(product);
		return convertToDTO(savedProduct);
	}

	@Cacheable("products")
	public List<DTOProduct> findAll() {
		return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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
	public DTOProduct updateProduct(Integer id, DTOProduct dtoProduct) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
		product.setName(dtoProduct.getName());
		product.setDescription(dtoProduct.getDescription());
		product.setPrice(dtoProduct.getPrice());
		product.setActive(dtoProduct.isActive());
		product.setStock(dtoProduct.getStock());
		Product updatedProduct = productRepository.save(product);
		return convertToDTO(updatedProduct);
	}
}
