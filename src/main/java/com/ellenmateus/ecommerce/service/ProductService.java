package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    private SaleService saleService;

    
    @Operation(summary = "Check if a product is on sale")
    public boolean isProductInAnySale(Long productId) {
        return saleService.isProductInAnySale(productId);
    }

    @Operation(summary = "Deactivate a product")
    @CacheEvict(value = "products", allEntries = true)
    public void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    
    @Operation(summary = "Delete a product by ID")
    @CacheEvict(value = "products", allEntries = true)
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Operation(summary = "Search all products")
    @Cacheable("products")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Operation(summary = "Search for a product by ID")
    @Cacheable(value = "products", key = "#id")
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    
    @Operation(summary = "Save a new product")
    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Operation(summary = "Update an existing product")
    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setActive(updatedProduct.getActive());
        return productRepository.save(product);
    }
}
