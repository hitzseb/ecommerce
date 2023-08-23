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
public class ProductService {
    private final ProductRepo repo;
    private final ImageService imageService;

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

    public Product saveProduct(ProductDto productDto) throws ServiceException {
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setImage(imageService.convertImageToByteArray(productDto.imageFile()));
        product.setStock(productDto.stock());
        product.setPrice(productDto.price());
        try {
            return repo.save(product);
        } catch (ServiceException e) {
            throw new ServiceException("No se pudo guardar el producto", e);
        }
    }

    public Product updateProduct(Long id, ProductDto productDto) throws EntityNotFoundException {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));

        if (productDto.imageFile() != null && !productDto.imageFile().isEmpty()) {
            byte[] image = imageService.convertImageToByteArray(productDto.imageFile());
            product.setImage(image);
        }

        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setStock(productDto.stock());
        product.setPrice(productDto.price());

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

    public void toggleFeatured(Long id) throws EntityNotFoundException {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id " + id));
        product.setFeatured(!product.isFeatured());
        repo.save(product);
    }
}
