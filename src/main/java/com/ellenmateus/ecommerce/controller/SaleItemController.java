package com.ellenmateus.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.SaleItem;
import com.ellenmateus.ecommerce.service.SaleItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/saleitems")
@Tag(name = "SaleItem", description = "Endpoints for managing sale items, which represent individual products within a sale.")
public class SaleItemController {

    @Autowired
    private SaleItemService saleItemService;

    @GetMapping
    @Operation(summary = "Get all sale items")
    public List<SaleItem> getAllSaleItems() {
        return saleItemService.getAllSaleItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a sale item by ID")
    public ResponseEntity<SaleItem> getSaleItemById(@PathVariable Integer id) {
        Optional<SaleItem> saleItem = saleItemService.getSaleItemById(id);
        if (saleItem.isPresent()) {
            return ResponseEntity.ok(saleItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new sale item")
    public SaleItem createSaleItem(@RequestBody SaleItem saleItem) {
        return saleItemService.createSaleItem(saleItem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sale item by ID")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Integer id) {
        Optional<SaleItem> saleItem = saleItemService.getSaleItemById(id);
        if (saleItem.isPresent()) {
            saleItemService.deleteSaleItem(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
