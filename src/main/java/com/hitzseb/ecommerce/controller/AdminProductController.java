package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductService service;

    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = service.findAllProducts();
        model.addAttribute("products", products);
        return "adm-product-list";
    }

    @GetMapping("/new")
    public String showProductNew(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "adm-product-new";
    }

    @PostMapping("/new/save")
    public String saveProduct(@ModelAttribute Product product) {
        service.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/{id}/edit")
    public String showProductUpdate(@PathVariable Long id, Model model) {
        Product product = service.findProductById(id);
        model.addAttribute("product", product);
        return "adm-product-edit";
    }

    @PostMapping("/{id}/edit/save")
    public String editProduct(@PathVariable Long id, Product product) {
        service.updateProduct(id, product);
        return "redirect:/admin/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteAnime(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/admin/product";
    }
}
