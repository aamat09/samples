package com.amatsolutions.samples.taskmanager.model.carmarketplace;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // Dealer or Service
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double cost;

    // Getters and Setters
}
