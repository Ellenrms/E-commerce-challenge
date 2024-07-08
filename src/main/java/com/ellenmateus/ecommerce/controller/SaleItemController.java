package com.ellenmateus.ecommerce.controller;

import com.ellenmateus.ecommerce.model.SaleItem;
import com.ellenmateus.ecommerce.service.SaleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/saleitems")
public class SaleItemController {

    @Autowired
    private SaleItemService saleItemService;

    @GetMapping
    public List<SaleItem> getAllSaleItems() {
        return saleItemService.getAllSaleItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleItem> getSaleItemById(@PathVariable Long id) {
        Optional<SaleItem> saleItem = saleItemService.getSaleItemById(id);
        if (saleItem.isPresent()) {
            return ResponseEntity.ok(saleItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public SaleItem createSaleItem(@RequestBody SaleItem saleItem) {
        return saleItemService.createSaleItem(saleItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Long id) {
        Optional<SaleItem> saleItem = saleItemService.getSaleItemById(id);
        if (saleItem.isPresent()) {
            saleItemService.deleteSaleItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
