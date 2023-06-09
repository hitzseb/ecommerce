package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Order;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;

public interface OrderService {
    void makeOrder(HttpSession session);
    Order getOrderById(Long id);
    Page<Order> getOrders(int page, int size);
}
