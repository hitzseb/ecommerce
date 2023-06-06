package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Cart;
import com.hitzseb.ecommerce.model.Product;
import com.hitzseb.ecommerce.repo.CartRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepo cartRepo;
    private final ProductService productService;

    public void addProductById(Long id, HttpSession session) {
        Product product = productService.findProductById(id);
        Cart cart = (Cart) session.getAttribute("cart");
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepo.save(cart);
    }

    public void removeProductById(Long id, HttpSession session) {
        Product product = productService.findProductById(id);
        Cart cart = (Cart) session.getAttribute("cart");
        List<Product> products = cart.getProducts();
        products.remove(product);
        cart.setProducts(products);
        cartRepo.save(cart);
    }

}
