package com.ellenmateus.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product", description = "Endpoints for product management")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "List all products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Operation(summary = "Search for a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        return ResponseEntity.ok(product);
    }

    
    @Operation(summary = "Add a new product")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(product);
    }
    
    
    @Operation(summary = "Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.isProductInAnySale(id)) {
            productService.deactivateProduct(id);
            return ResponseEntity.ok().build();
        } else {
            productService.deleteById(id);
            return ResponseEntity.ok().build();
            }
    }
    
        
        @Operation(summary = "Deactivate a product")
        @PutMapping("/{id}/deactivate")
        public ResponseEntity<Void> deactivateProduct(@PathVariable Long id) {
            productService.deactivateProduct(id);
            return ResponseEntity.noContent().build();
        }
    }
  
