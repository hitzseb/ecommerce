package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.dto.ProductDto;
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
    public List<Product> searchProducts(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> findFeaturedProducts() {
        return repo.findByIsFeaturedTrue();
    }

    @Override
    public Product findProductById(Long id) throws EntityNotFoundException {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
    }

    @Override
    public Product saveProduct(ProductDto productDto) throws ServiceException {
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setImage(productDto.image());
        product.setStock(productDto.stock());
        product.setPrice(productDto.price());
        try {
            return repo.save(product);
        } catch (ServiceException e) {
            throw new ServiceException("No se pudo guardar el producto", e);
        }
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) throws EntityNotFoundException {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
        if (product.getName() != productDto.name()) {
            product.setName(productDto.name());
        }
        if (product.getDescription() != productDto.description()) {
            product.setDescription(productDto.description());
        }
        if (product.getImage() != productDto.image()) {
            product.setImage(productDto.image());
        }
        if (product.getPrice() != productDto.price()) {
            product.setPrice(productDto.price());
        }
        if (product.getStock() != productDto.stock()) {
            product.setStock(productDto.stock());
        }
        return repo.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws EntityNotFoundException, ServiceException {
        repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
        try {
            repo.deleteById(id);
        } catch (ServiceException e) {
            throw new ServiceException("No se ha podido eliminar el producto con id " + id, e);
        }
    }
}
