package org.example.productservice.util;

import lombok.RequiredArgsConstructor;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductData implements CommandLineRunner {


    private final ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {

        if(productRepository.count() <1){
            Product product = new Product();
            product.setDescription("Samsung Note");
            product.setName("Samsung Note 2");
            product.setPrice(BigDecimal.valueOf(10000));
            productRepository.save(product);

        }
    }
}
