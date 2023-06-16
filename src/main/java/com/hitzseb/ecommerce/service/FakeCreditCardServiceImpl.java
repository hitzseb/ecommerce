package com.hitzseb.ecommerce.service;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.dto.CreditCard;
import com.hitzseb.ecommerce.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class FakeCreditCardServiceImpl implements FakeCreditCardService {
    private final UserService userService;

    Faker faker = new Faker();

    public CreditCard generateCreditCardData(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        String number = faker.number().numberBetween(1000, 9999) + "-" + faker.number().numberBetween(1000, 9999) + "-" + faker.number().numberBetween(1000, 9999) + "-" + faker.number().numberBetween(1000, 9999);
        String owner = user.getName();
        LocalDate localDate = LocalDate.now().plusYears(faker.number().numberBetween(1, 10));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YY");
        String date = localDate.format(formatter);
        int cvv = faker.random().nextInt(100, 999);
        CreditCard creditCard = new CreditCard(owner, number, date, cvv);
        return creditCard;
    }

}
