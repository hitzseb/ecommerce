package com.hitzseb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private double price;
    private double total;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
}
