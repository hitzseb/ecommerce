package com.hitzseb.ecommerce.controller.api;

import com.hitzseb.ecommerce.model.Order;
import com.hitzseb.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = service.getAllOrders();
        return ResponseEntity.ok(orderList);
    }
}
