package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByIsFeaturedTrue();
    Page<Product> findAll(Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name);
}
