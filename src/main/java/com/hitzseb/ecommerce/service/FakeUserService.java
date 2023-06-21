package com.hitzseb.ecommerce.service;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.model.Role;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FakeUserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    Faker faker = new Faker();

    public void createFakeUser(String name, String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setName(name);
        user.setAddress(faker.address().fullAddress());
        user.setEnabled(true);
        userRepo.save(user);
    }

    public void createFakeUsers(int amount) {
        List<User> userList = Stream.generate(() -> {
                    User user = new User();
                    user.setName(faker.funnyName().name());
                    user.setUsername(faker.internet().emailAddress());
                    user.setAddress(faker.address().fullAddress());
                    user.setEnabled(true);
                    return user;
                }).limit(amount)
                .collect(Collectors.toList());

        userRepo.saveAll(userList);
    }

}
