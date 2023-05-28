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
    private String orderNumber;
    private LocalDateTime creationDate;
    private LocalDateTime receptionDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    List<Detail> details;
}
