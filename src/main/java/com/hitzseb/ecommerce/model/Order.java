package com.hitzseb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buyer;
    private String address;
    private String orderNumber;
    private LocalDateTime date = LocalDateTime.now();
    private double total;
    @OneToMany(mappedBy = "order")
    private List<Detail> details;
    @ManyToOne
    private User user;
}
