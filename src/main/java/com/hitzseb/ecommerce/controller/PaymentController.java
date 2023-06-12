package com.hitzseb.ecommerce.controller;

import com.github.javafaker.Faker;
import com.hitzseb.ecommerce.model.CreditCard;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.service.CreditCardValidationService;
import com.hitzseb.ecommerce.service.OrderService;
import com.hitzseb.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final UserService userService;
    private final OrderService orderService;
    private final CreditCardValidationService validationService;

    @GetMapping
    public String showPayment(Model model, HttpSession session) {
        Faker faker = new Faker();
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        String number = faker.finance().creditCard();
        String owner = user.getName();
        LocalDate localDate = LocalDate.now().plusYears(faker.number().numberBetween(1, 10));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/YY");
        String date = localDate.format(formatter);
        int cvv = faker.random().nextInt(100, 999);
        CreditCard creditCard = new CreditCard(owner, number, date, cvv);
        model.addAttribute("creditCard", creditCard);
        model.addAttribute("role", session.getAttribute("role"));
        System.out.println(owner);
        return "payment";
    }

    @PostMapping("/pay")
    public String pay(@ModelAttribute("creditCard") CreditCard creditCard, Model model, HttpSession session) {
        boolean isValid = validationService.validateCreditCard(creditCard);
        if (!isValid) {
            model.addAttribute("role", session.getAttribute("role"));
            model.addAttribute("creditCardError", "Datos inváidos");
            return "payment";
        }
        System.out.println(validationService.validateCreditCard(creditCard));
        orderService.makeOrder(session);
        return "redirect:/order";
    }

}
