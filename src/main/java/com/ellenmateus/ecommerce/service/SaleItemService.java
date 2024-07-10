package com.ellenmateus.ecommerce.service;


import com.ellenmateus.ecommerce.model.SaleItem;
import com.ellenmateus.ecommerce.repository.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    public List<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }

    public Optional<SaleItem> getSaleItemById(Integer id) {
        return saleItemRepository.findById(id);
    }

    public SaleItem createSaleItem(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public void deleteSaleItem(Integer id) {
        saleItemRepository.deleteById(id);
    }

 
}
