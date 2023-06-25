package com.hitzseb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private double price;
    private int stock;
    private boolean isFeatured = false;
}
