package com.example.shopify_clothes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories("com.example.shopify_clothes.repository")
@EntityScan("com.example.shopify_clothes.model")
public class ShopifyClothesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopifyClothesApplication.class, args);
    }

}
