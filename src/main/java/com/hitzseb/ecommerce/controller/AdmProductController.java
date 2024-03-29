package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.dto.ProductDto;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdmProductController {
    private final ProductService service;

    @GetMapping
    public String showProductList(Model model, HttpSession session) {
        List<Product> products = service.findAllProducts();
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("products", products);
        return "product-table";
    }

    @GetMapping("/new")
    public String showProductNew(Model model, HttpSession session) {
        Product product = new Product();
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("product", product);
        return "product-new";
    }

    @PostMapping("/new/save")
    public String saveProduct(@ModelAttribute ProductDto productDto) {
        service.saveProduct(productDto);
        return "redirect:/admin/product";
    }

    @GetMapping("/{id}/edit")
    public String showProductUpdate(@PathVariable Long id, Model model, HttpSession session) {
        Product product = service.findProductById(id);
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/{id}/edit/save")
    public String editProduct(@PathVariable Long id, ProductDto productDto) {
        service.updateProduct(id, productDto);
        return "redirect:/admin/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteAnime(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/admin/product";
    }

//    @PostMapping("/{id}/featured")
//    public void printFeatured(@PathVariable Long id) {
//        Product product = service.findProductById(id);
//        System.out.println(product.isFeatured());
//    }

    @PostMapping("/{id}/toggleFeatured")
    public String toggleFeatured(@RequestParam Long id) {
        service.toggleFeatured(id);
        return "redirect:/admin/product";
    }

}
