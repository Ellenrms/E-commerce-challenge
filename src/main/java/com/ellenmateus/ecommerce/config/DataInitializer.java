package com.ellenmateus.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ellenmateus.ecommerce.dto.DTOProduct;
import com.ellenmateus.ecommerce.service.ProductService;

@Component
public class DataInitializer implements CommandLineRunner {



    @Autowired
    private ProductService productService;

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
    }
}
