package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleService saleService;

    public boolean isProductInAnySale(Long productId) {
        return saleService.isProductInAnySale(productId);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Cacheable("products")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    
    @Cacheable(value = "products", key = "#id")
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + id));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setActive(updatedProduct.getActive());
        return productRepository.save(product);
    }
}
