package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Product;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CartService {
    void addProductById(Long id, HttpSession session);
    void removeProductById(Long id, HttpSession session);
    List<Product> getCartProducts(HttpSession session);
    double getTotalPrice(List<Product> products);
}
