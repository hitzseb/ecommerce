package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.repo.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;

    public List<Product> findAllProducts() {
        return repo.findAll();
    }

    public Page<Product> findPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }

    public List<Product> searchProducts(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findFeaturedProducts() {
        return repo.findByIsFeaturedTrue();
    }

    public Product findProductById(Long id) throws EntityNotFoundException {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
    }

    public Product saveProduct(Product product) throws ServiceException {
        try {
            return repo.save(product);
        } catch (ServiceException e) {
            throw new ServiceException("No se pudo guardar el producto", e);
        }
    }

    public Product updateProduct(Long id, Product updatedProduct) throws EntityNotFoundException {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
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
        return repo.save(product);
    }

    public void deleteProduct(Long id) throws EntityNotFoundException, ServiceException {
        repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
        try {
            repo.deleteById(id);
        } catch (ServiceException e) {
            throw new ServiceException("No se ha podido eliminar el producto con id " + id, e);
        }
    }
}
