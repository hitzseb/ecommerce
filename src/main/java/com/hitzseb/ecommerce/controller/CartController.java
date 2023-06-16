package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public String showCart(Model model, HttpSession session) {
        List<Product> products = cartService.getCartProducts(session);
        double total = cartService.getTotalPrice(products);
        model.addAttribute("cart", products);
        model.addAttribute("total", "$" + total);
        model.addAttribute("role", session.getAttribute("role"));
        if (total > 0) {
            model.addAttribute("isNotEmpty", true);
        }
        return "cart";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam Long productId, HttpSession session) {
        cartService.addProductById(productId, session);
        return "redirect:/product";
    }

    @GetMapping("/remove")
    public String removeProduct(@RequestParam Long productId, HttpSession session) {
        cartService.removeProductById(productId, session);
        return "redirect:/cart";
    }

}
