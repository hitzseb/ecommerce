package com.hitzseb.ecommerce.service;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.enums.Role;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeUserServiceImpl implements FakeUserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    Faker faker = new Faker();

    @Override
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

    @Override
    public void createFakeUsers (int amount) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            User user = new User();
            user.setName(faker.funnyName().name());
            user.setUsername(faker.internet().emailAddress());
            user.setAddress(faker.address().fullAddress());
            user.setEnabled(true);
            userList.add(user);
        }
        userRepo.saveAll(userList);
    }

}
