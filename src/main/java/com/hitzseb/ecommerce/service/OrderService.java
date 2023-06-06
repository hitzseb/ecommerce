package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Cart;
import com.hitzseb.ecommerce.model.Order;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.repo.OrderRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ProductService productService;

    public void makeOrder(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Cart cart = (Cart) session.getAttribute("cart");
        List<Product> products = cart.getProducts();
        double total = 0;
        Order order = new Order();
        order.setOrderNumber(String.format("%010d", order.getId()));
        order.setDate(LocalDateTime.now());
        order.setUser(userService.findUserById(userId).get());
        order.setProducts(products);
        for (Product product : products) {
            product.setStock(product.getStock() - 1);
            productService.saveProduct(product);
            total += product.getPrice();
        }
        order.setTotal(total);
        orderRepo.save(order);
    }

}
