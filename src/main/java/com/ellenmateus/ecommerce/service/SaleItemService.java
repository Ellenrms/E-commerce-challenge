package com.ellenmateus.ecommerce.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOSaleItem;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.model.Sale;
import com.ellenmateus.ecommerce.model.SaleItem;
import com.ellenmateus.ecommerce.repository.ProductRepository;
import com.ellenmateus.ecommerce.repository.SaleItemRepository;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;
    
    @Autowired
	private SaleService saleService;
    
    @Autowired
    private ProductRepository productRepository; 
    

    
    
    public List<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }

    public Optional<SaleItem> getSaleItemById(Integer id) {
        return saleItemRepository.findById(id);
    }

    
    @CacheEvict(value = "sales", allEntries = true)
    public SaleItem createSaleItem(DTOSaleItem dto, Sale sale) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + dto.getProductId()));
        
        SaleItem saleItem = new SaleItem();
        saleItem.setProduct(product);
        saleItem.setQuantity(dto.getQuantity());
        saleItem.setPrice(dto.getPrice());
        saleItem.setSale(sale);
        
        return saleItemRepository.save(saleItem);
        
    }
    

    public void deleteSaleItem(Integer id) {
        saleItemRepository.deleteById(id);
    }

 
}
