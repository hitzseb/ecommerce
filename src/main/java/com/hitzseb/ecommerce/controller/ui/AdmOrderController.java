package com.hitzseb.ecommerce.controller.ui;

import com.hitzseb.ecommerce.model.Order;
import com.hitzseb.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdmOrderController {
    private final OrderService orderService;

    @GetMapping
    public String showOrderList(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "20") int size,
                                Model model, HttpSession session) {
        Page<Order> orderPage = orderService.getPaginatedOrders(page, size);
        List<Order> orderList = orderPage .getContent();
        int totalPages = orderPage.getTotalPages();
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("orders", orderList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "order-table";
    }
}
