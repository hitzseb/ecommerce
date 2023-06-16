package com.hitzseb.ecommerce.controller.ui;

import com.hitzseb.ecommerce.dto.CreditCard;
import com.hitzseb.ecommerce.service.CreditCardValidationService;
import com.hitzseb.ecommerce.service.FakeCreditCardService;
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

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final UserService userService;
    private final OrderService orderService;
    private final CreditCardValidationService validationService;
    private final FakeCreditCardService fakeCreditCardService;

    @GetMapping
    public String showPayment(Model model, HttpSession session) {
        CreditCard creditCard = fakeCreditCardService.generateCreditCardData(session);
        model.addAttribute("creditCard", creditCard);
        model.addAttribute("role", session.getAttribute("role"));
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
