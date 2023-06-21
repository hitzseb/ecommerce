package com.hitzseb.ecommerce.service;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FakeProductService {
    private final ProductRepo productRepo;

    Faker faker = new Faker();

    public void createFakeProducts(int amount, boolean featured) {
        List<Product> products = Stream.generate(() -> {
                    Product product = new Product();
                    double price = Math.random() * 1000;
                    double roundedPrice = Math.round(price * 100.0) / 100.0;
                    String name = faker.commerce().productName();
                    String description = faker.lorem().sentence(10);
                    product.setName(name);
                    product.setDescription(description);
                    product.setImage("https://i.imgur.com/5Upm1Ag.jpg");
                    product.setStock((int) (Math.random() * 10) + 1);
                    product.setPrice(roundedPrice);
                    product.setFeatured(featured);
                    return product;
                }).limit(amount)
                .collect(Collectors.toList());

        productRepo.saveAll(products);
    }

}
