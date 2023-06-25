package com.hitzseb.ecommerce;

import com.hitzseb.ecommerce.enums.Role;
import com.hitzseb.ecommerce.service.FakeProductService;
import com.hitzseb.ecommerce.service.FakeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class EcommerceApplication implements ApplicationRunner {
	private final FakeUserService fakeUserService;
	private final FakeProductService fakeProductService;

	@Value("${user.test.username}")
	private String userUsername;
	@Value("${user.test.password}")
	private String userPassword;
	@Value("${admin.test.username}")
	private String adminUsername;
	@Value("${admin.test.password}")
	private String adminPassword;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		fakeProductService.createFakeProducts(8, true);
		fakeProductService.createFakeProducts(24, false);

		fakeUserService.createFakeUser("User", userUsername, userPassword, Role.USER);
		fakeUserService.createFakeUser("Admin", adminUsername, adminPassword, Role.ADMIN);

		fakeUserService.createFakeUsers(20);

	}
}
