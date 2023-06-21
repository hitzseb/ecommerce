package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final UserService userService;

    public void addProductById(Long id, HttpSession session) {
        Product product = productService.findProductById(id);
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        List<Product> cart = user.getCart();
        cart.add(product);
        user.setCart(cart);
        userService.updateUser(user);
    }

    public void removeProductById(Long id, HttpSession session) {
        Product product = productService.findProductById(id);
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        List<Product> cart = user.getCart();
        cart.remove(product);
        user.setCart(cart);
        userService.updateUser(user);
    }

    public List<Product> getCartProducts(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        return user.getCart();
    }

    public double getTotalPrice(List<Product> products) {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        return Math.floor(total * 100) / 100;
    }

    public void clearCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        user.setCart(new ArrayList<>());
        userService.updateUser(user);
    }

}
