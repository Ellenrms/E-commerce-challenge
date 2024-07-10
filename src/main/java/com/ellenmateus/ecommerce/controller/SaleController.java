package com.ellenmateus.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.Sale;
import com.ellenmateus.ecommerce.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "Endpoints for managing sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    @Operation(summary = "Get all sales")
    public List<Sale> getAllSales() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a sale by ID")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Optional<Sale> sale = saleService.findById(id);
        if (sale.isPresent()) {
            return ResponseEntity.ok(sale.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new sale")
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a sale by ID")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        return saleService.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sale by ID")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        Optional<Sale> sale = saleService.findById(id);
        if (sale.isPresent()) {
            saleService.deleteSale(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date")
    @Operation(summary = "Find sales by date range")
    public List<Sale> findSalesByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return saleService.findSalesByDate(startDate, endDate);
    }

    @GetMapping("/report/monthly")
    @Operation(summary = "Generate monthly sales report")
    public List<Sale> generateMonthlySalesReport(@RequestParam int year, @RequestParam int month) {
        return saleService.generateMonthlySalesReport(year, month);
    }

    @GetMapping("/report/weekly")
    @Operation(summary = "Generate weekly sales report")
    public List<Sale> generateWeeklySalesReport(@RequestParam int year, @RequestParam int week) {
        return saleService.generateWeeklySalesReport(year, week);
    }
}
