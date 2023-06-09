package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Order;
import jakarta.servlet.http.HttpSession;

public interface OrderService {
    void makeOrder(HttpSession session);
    Order getOrderById(Long id);
}
