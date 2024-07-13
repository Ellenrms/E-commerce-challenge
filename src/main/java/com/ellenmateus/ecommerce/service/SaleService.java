package com.ellenmateus.ecommerce.service;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOSale;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.Product;
import com.ellenmateus.ecommerce.model.Sale;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.SaleRepository;
import com.ellenmateus.ecommerce.repository.UserRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    
    @Autowired
    private UserRepository userRepository;

    
    
    @CacheEvict(value = "sales", allEntries = true)
    public Sale createSale(DTOSale dto) {
    		User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));
    		
    				Sale sale = new Sale();
    				sale.setUser(user);
    				sale.setSaleDate(dto.getSaleDate());
    				sale.setTotalAmount(dto.getTotalAmount());
    				return saleRepository.save(sale);
    }
    
    
    @Cacheable(value = "sales")
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }
    
    
    @Cacheable(value = "sales", key = "#id")
    public Optional<Sale> findById(Integer id) {
        return saleRepository.findById(id);
    }
    
    
    @Cacheable(value = "sales", key = "#id")
    public Sale findSaleById(Integer id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + id));
    }
    
    
    public Sale updateSale(Integer id, Sale updatedSale) {
    			Sale sale = findSaleById(id);

    				sale.setSaleDate(updatedSale.getSaleDate());
    				sale.setUser(updatedSale.getUser());
    				sale.setItems(updatedSale.getItems());
    				sale.setPayment(updatedSale.getPayment());
    				return saleRepository.save(sale);
    }

    
    
    public void deleteSale(Integer id) {
        Sale sale = findSaleById(id);
        saleRepository.delete(sale);
    }

    
    
    public List<Sale> findSalesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }

    
    
    public List<Sale> generateMonthlySalesReport(int year, int month) {
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);
        return saleRepository.findBySaleDateBetween(startOfMonth, endOfMonth);
    }

    
    
    public List<Sale> generateWeeklySalesReport(int year, int week) {
        LocalDateTime startOfWeek = LocalDateTime.now().withYear(year)
                .with(WeekFields.ISO.weekOfYear(), week).with(WeekFields.ISO.dayOfWeek(), 1);
        LocalDateTime endOfWeek = startOfWeek.plusDays(6).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return saleRepository.findBySaleDateBetween(startOfWeek, endOfWeek);
    }

    
    public boolean isProductInAnySale(Integer productId) {
        List<Sale> sales = saleRepository.findAll();
        for (Sale sale : sales) {
            for (Product product : sale.getItems().stream().map(item -> item.getProduct()).toList()) {
                if (product.getId().equals(productId)) {
                    return true;
                }
            }
        }
        return false;
    }
}