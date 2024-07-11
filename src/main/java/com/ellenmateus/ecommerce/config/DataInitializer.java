package com.ellenmateus.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ellenmateus.ecommerce.dto.DTOProduct;
import com.ellenmateus.ecommerce.dto.DTOUser;
import com.ellenmateus.ecommerce.service.ProductService;
import com.ellenmateus.ecommerce.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        
        DTOProduct product1 = new DTOProduct(null, "Reagente de Hematologia", "Reagente usado para análise de hemograma", 150.00, true, 100);
        DTOProduct product2 = new DTOProduct(null, "Kit de Coleta de Sangue", "Kit para coleta de amostras de sangue", 50.00, true, 200);
        DTOProduct product3 = new DTOProduct(null, "Microscópio Clínico", "Microscópio utilizado para exames clínicos", 3000.00, true, 10);
        DTOProduct product4 = new DTOProduct(null, "Centrífuga Laboratorial", "Equipamento para separação de amostras", 1200.00, true, 15);
        DTOProduct product5 = new DTOProduct(null, "Pipeta Automática", "Pipeta usada para medição precisa de líquidos", 400.00, true, 50);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);

        DTOUser user1 = new DTOUser(null, "johndoe", "john.doe@example.com", "password123", "Sao Paulo");
        DTOUser user2 = new DTOUser(null, "janedoe", "jane.doe@example.com", "password123", "Campinas");
        DTOUser user3 = new DTOUser(null, "michael", "michael@example.com", "password123", "Barretos");
        DTOUser user4 = new DTOUser(null, "sarah", "sarah@example.com", "password123", "Rio de Janeiro");
        DTOUser user5 = new DTOUser(null, "alice", "alice@example.com", "password123", "Sorocaba");
        DTOUser user6 = new DTOUser(null, "bob", "bob@example.com", "password123", "Manaus");
        DTOUser user7 = new DTOUser(null, "charlie", "charlie@example.com", "password123", "Mococa");
        DTOUser user8 = new DTOUser(null, "david", "david@example.com", "password123", "Casa Branca");

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);
    }
}
