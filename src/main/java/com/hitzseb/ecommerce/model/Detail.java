package com.hitzseb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "details")
@Data
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}
