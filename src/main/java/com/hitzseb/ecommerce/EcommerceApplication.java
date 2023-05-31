package com.hitzseb.ecommerce;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.model.Role;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.ProductRepo;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void run(ApplicationArguments args) throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("admin");
		user.setEmail("admin@ecommerce.com");
		user.setPassword("admin");
		user.setRole(Role.ADMIN);
		user.setEnabled(true);
		userRepo.save(user);

		List<Product> products = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			Product product = new Product();
			double price = Math.random() * 1000;
			double roundedPrice = Math.round(price * 100.0) / 100.0;
			product.setId((long)i);
			product.setName("Producto de prueba " + i);
			product.setDescription("Este es un producto para ver como se ve la lista de productos y probar las funcionalidades update y delete.");
			product.setImage("/images/default.jpg");
			product.setStock((int) (Math.random() * 100) + 1);
			product.setPrice(roundedPrice);
			product.setUser(user);
			products.add(product);
		}

		productRepo.saveAll(products);
	}
}
