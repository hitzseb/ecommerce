package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
