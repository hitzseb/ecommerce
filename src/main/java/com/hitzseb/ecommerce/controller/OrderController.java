package com.hitzseb.ecommerce.controller;

import com.hitzseb.ecommerce.model.Order;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.service.OrderService;
import com.hitzseb.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String showOrderList(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();
        List<Order> orders = user.getOrders();
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable Long id, Model model, HttpSession session) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/makeOrder")
    public String makeOrder(HttpSession session) {
        orderService.makeOrder(session);
        return "redirect:/order";
    }
}
