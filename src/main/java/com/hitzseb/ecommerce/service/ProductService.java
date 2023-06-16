package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.dto.ProductDto;
import com.hitzseb.ecommerce.model.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();
    public Page<Product> findPaginatedProducts(int page, int size);
    public List<Product> searchProducts(String name);
    public List<Product> findFeaturedProducts();
    public Product findProductById(Long id) throws EntityNotFoundException;
    public Product saveProduct(ProductDto productDto);
    public Product updateProduct(Long id, ProductDto productDto) throws EntityNotFoundException;
    public void deleteProduct(Long id);
}
