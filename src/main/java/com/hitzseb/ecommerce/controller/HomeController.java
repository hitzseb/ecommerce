package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public String showHome(Model model, HttpSession session) {
        List<Product> featuredProducts = productService.findFeaturedProducts();
        model.addAttribute("role", session.getAttribute("userRole"));
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("products", featuredProducts);
        return "home";
    }

}
