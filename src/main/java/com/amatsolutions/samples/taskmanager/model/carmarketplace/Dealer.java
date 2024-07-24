package com.amatsolutions.samples.taskmanager.model.carmarketplace;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String inventorySoftware;

    @OneToMany(mappedBy = "dealer")
    private List<Car> inventory;

    // Getters and Setters
}
