package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT MAX(o.id) FROM Order o")
    Long getLastGeneratedId();
}
