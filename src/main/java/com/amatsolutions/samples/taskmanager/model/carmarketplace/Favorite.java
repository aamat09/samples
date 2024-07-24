package com.amatsolutions.samples.taskmanager.model.carmarketplace;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Getters and Setters
}
