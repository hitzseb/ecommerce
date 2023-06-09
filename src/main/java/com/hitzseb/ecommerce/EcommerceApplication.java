package com.hitzseb.ecommerce;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.*;
import com.hitzseb.ecommerce.repo.ProductRepo;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class EcommerceApplication implements ApplicationRunner {
	private final ProductRepo productRepo;
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		Faker faker = new Faker();

		User user = new User();
		user.setPassword(passwordEncoder.encode("user"));
		user.setUsername("user@ecommerce.com");
		user.setRole(Role.USER);
		user.setName(faker.funnyName().name());
		user.setAddress(faker.address().fullAddress());
		user.setEnabled(true);
		userRepo.save(user);

		User admin = new User();
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setUsername("admin@ecommerce.com");
		admin.setRole(Role.ADMIN);
		admin.setName(faker.funnyName().name());
		admin.setEnabled(true);
		userRepo.save(admin);

		List<Product> products = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			Product product = new Product();
			double price = Math.random() * 1000;
			double roundedPrice = Math.round(price * 100.0) / 100.0;
			String name = faker.commerce().productName();
			String description = faker.lorem().sentence(10);
			product.setName(name);
			product.setDescription(description);
			product.setImage("/images/default.jpg");
			product.setStock((int) (Math.random() * 10) + 1);
			product.setPrice(roundedPrice);
			product.setFeatured(true);
			products.add(product);
		}

		for (int i = 0; i < 24; i++) {
			Product product = new Product();
			double price = Math.random() * 1000;
			double roundedPrice = Math.round(price * 100.0) / 100.0;
			String name = faker.commerce().productName();
			String description = faker.lorem().sentence(10);
			product.setName(name);
			product.setDescription(description);
			product.setImage("/images/default.jpg");
			product.setStock((int) (Math.random() * 10) + 1);
			product.setPrice(roundedPrice);
			products.add(product);
		}

		productRepo.saveAll(products);
	}
}
