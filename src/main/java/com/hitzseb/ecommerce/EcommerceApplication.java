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

		Product product = new Product();
		product.setId(1L);
		product.setName("Producto de prueba");
		product.setDescription("Este es un producto para probar las funcionalidades de update y delete");
		product.setImage("https://img.freepik.com/foto-gratis/mostrando-carro-carro-compras-linea-signo-grafico_53876-133967.jpg");
		product.setStock(4);
		product.setPrice(250);
		product.setUser(user);
		productRepo.save(product);
	}

}
