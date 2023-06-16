package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Order;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    void makeOrder(HttpSession session);
    Order getOrderById(Long id);
    Page<Order> getPaginatedOrders(int page, int size);

    List<Order> getAllOrders();
}
