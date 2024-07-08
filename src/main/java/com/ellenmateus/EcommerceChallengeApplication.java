package com.ellenmateus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ellenmateus.ecommerce.repository")
public class EcommerceChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceChallengeApplication .class, args);
    }
}
