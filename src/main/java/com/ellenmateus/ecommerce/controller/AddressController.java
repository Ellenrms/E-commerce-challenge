package com.ellenmateus.ecommerce.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.Address;
import com.ellenmateus.ecommerce.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Addresses", description = "Endpoints for managing user addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @Operation(summary = "Get all addresses")
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an address by ID")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new address")
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        Optional<Address> address = addressService.findById(id);
        if (address.isPresent()) {
            Address updatedAddress = address.get();
            updatedAddress.setStreet(addressDetails.getStreet());
            updatedAddress.setCity(addressDetails.getCity());
            updatedAddress.setState(addressDetails.getState());
            updatedAddress.setPostalCode(addressDetails.getPostalCode());
            updatedAddress = addressService.save(updatedAddress);
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an address by ID")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if (address.isPresent()) {
            addressService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
