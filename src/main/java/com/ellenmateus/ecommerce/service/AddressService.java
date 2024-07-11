package com.ellenmateus.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.dto.DTOAddress;
import com.ellenmateus.ecommerce.exception.ResourceNotFoundException;
import com.ellenmateus.ecommerce.model.Address;
import com.ellenmateus.ecommerce.model.User;
import com.ellenmateus.ecommerce.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
     private AddressRepository addressRepository;

    @Autowired
    private UserService userService;
    
    
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    public Address save(DTOAddress dto) {
    	User user = userService.getUserById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Not Found"));	
    	Address address = new Address();
    	address.setCity(dto.getCity());
        address.setUser(user);
        return addressRepository.save(address);
    }
    
    public Address update(Integer id, DTOAddress dto) {
    	User user = userService.getUserById(dto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Not Found"));	
    	Address address = findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));	
        address.setCity(dto.getCity());
        address.setUser(user);
       
    	return addressRepository.save(address);
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }
}