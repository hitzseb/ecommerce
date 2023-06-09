package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    @Override
    public List<Product> findAllProducts() {
        return repo.findAll();
    }

    @Override
    public Page<Product> findPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    @Override
    public List<Product> findFeaturedProducts() {
        return repo.findByIsFeaturedTrue();
    }

    @Override
    public Product findProductById(Long id) throws EntityNotFoundException {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found with id " + id));
    }

    @Override
    public void saveProduct(Product product) {
        repo.save(product);
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) throws EntityNotFoundException {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found with id " + id));
        if (product.getName() != updatedProduct.getName()) {
            product.setName(updatedProduct.getName());
        }
        if (product.getDescription() != updatedProduct.getDescription()) {
            product.setDescription(updatedProduct.getDescription());
        }
        if (product.getImage() != updatedProduct.getImage()) {
            product.setImage(updatedProduct.getImage());
        }
        if (product.getPrice() != updatedProduct.getPrice()) {
            product.setPrice(updatedProduct.getPrice());
        }
        if (product.getStock() != updatedProduct.getStock()) {
            product.setStock(updatedProduct.getStock());
        }
        repo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
