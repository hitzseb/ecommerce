package com.hitzseb.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

public record ProductDto(String name, String description, MultipartFile imageFile, double price, int stock) {
}
