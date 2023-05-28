package com.hitzseb.ecommerce.repo;

import com.hitzseb.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
