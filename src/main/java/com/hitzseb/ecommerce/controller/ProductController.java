package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "16") int size,
                               Model model, HttpSession session) {
        Page<Product> productsPage = productService.findPaginatedProducts(page, size);
        List<Product> products = productsPage.getContent();
        int totalPages = productsPage.getTotalPages();
        model.addAttribute("role", session.getAttribute("userRole"));
        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "product-list";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model, HttpSession session) {
        Product product = productService.findProductById(id);
        model.addAttribute("role", session.getAttribute("userRole"));
        model.addAttribute("product", product);
        return "product";
    }

}
