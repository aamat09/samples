package com.amatsolutions.samples.taskmanager.model.carmarketplace;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int year;
    private double price;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    // Getters and Setters
}
