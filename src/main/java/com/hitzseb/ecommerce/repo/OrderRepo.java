package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT MAX(o.id) FROM Order o")
    Long getLastGeneratedId();
    @Query("SELECT o FROM Order o ORDER BY o.date DESC")
    Page<Order> findPaginatedOrders(Pageable pageable);
}
