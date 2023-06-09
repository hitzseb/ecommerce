package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();
    public Page<Product> findPaginatedProducts(int page, int size);
    public List<Product> findFeaturedProducts();
    public Product findProductById(Long id) throws EntityNotFoundException;
    public void saveProduct(Product product);
    public void updateProduct(Long id, Product updatedProduct) throws EntityNotFoundException;
    public void deleteProduct(Long id);
}
