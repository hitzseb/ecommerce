package com.hitzseb.ecommerce;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.*;
import com.hitzseb.ecommerce.repo.ProductRepo;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${admin.test.username}")
	private String adminUsername;
	@Value("${admin.test.password}")
	private String adminPassword;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		Faker faker = new Faker();

		User admin = new User();
		admin.setUsername(adminUsername);
		admin.setPassword(passwordEncoder.encode(adminPassword));
		admin.setRole(Role.ADMIN);
		admin.setName("Admin");
		admin.setEnabled(true);
		userRepo.save(admin);

		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			User randomUser = new User();
			randomUser.setName(faker.funnyName().name());
			randomUser.setUsername(faker.internet().emailAddress());
			randomUser.setAddress(faker.address().fullAddress());
			randomUser.setEnabled(true);
			userList.add(randomUser);
		}
		userRepo.saveAll(userList);

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
