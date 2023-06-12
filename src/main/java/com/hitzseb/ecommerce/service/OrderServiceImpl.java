package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Detail;
import com.hitzseb.ecommerce.model.Order;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.model.User;
import com.hitzseb.ecommerce.repo.OrderRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserServiceImpl userService;
    private final CartService cartService;
    private final DetailService detailService;
    private final OrderRepo orderRepo;

    @Override
    public void makeOrder(HttpSession session) {
        Long userId = (Long) session.getAttribute("id");
        User user = userService.findUserById(userId).get();

        List<Product> products = cartService.getCartProducts(session);
        Order order = new Order();
        double total = cartService.getTotalPrice(products);

        String orderNumber;
        if (orderRepo.getLastGeneratedId() != null) {
            orderNumber = String.format("%010d", orderRepo.getLastGeneratedId() + 1);
        } else {
            orderNumber = String.format("%010d", 1);
        }

        List<Detail> detailList = new ArrayList<>();
        for (Product product : products) {
            Detail detail = new Detail();
            detail.setName(product.getName());
            detail.setPrice(product.getPrice());
            detailList.add(detail);
        }
        detailService.saveAllDetails(detailList);

        if (total > 0) {
            order.setUser(user);
            order.setBuyer(user.getName());
            order.setAddress(user.getAddress());
            order.setOrderNumber(orderNumber);
            order.setDetails(detailList);
            order.setTotal(total);

            orderRepo.save(order);

            List<Order> orderList = user.getOrders();
            orderList.add(order);
            user.setOrders(orderList);
            userService.updateUser(user);
        }

        cartService.clearCart(session);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public Page<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepo.findPaginatedOrders(pageable);
    }
}
