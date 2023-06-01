package com.hitzseb.ecommerce;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.model.Role;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.ProductRepo;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class EcommerceApplication implements ApplicationRunner {

	private final UserRepo userRepo;
	private final ProductRepo productRepo;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		User user = new User();
		user.setId(1L);
		user.setUsername("admin");
		user.setEmail("admin@ecommerce.com");
		user.setPassword("admin");
		user.setRole(Role.ADMIN);
		user.setEnabled(true);
		userRepo.save(user);

		List<Product> products = new ArrayList<>();
		Faker faker = new Faker();

		for (int i = 1; i <= 20; i++) {
			Product product = new Product();
			double price = Math.random() * 1000;
			double roundedPrice = Math.round(price * 100.0) / 100.0;
			String name = faker.commerce().productName();
			String description = faker.lorem().sentence(10);
			product.setId((long)i);
			product.setName(name);
			product.setDescription(description);
			product.setImage("/images/default.jpg");
			product.setStock((int) (Math.random() * 100) + 1);
			product.setPrice(roundedPrice);
			product.setUser(user);
			products.add(product);
		}

		productRepo.saveAll(products);
	}
}
