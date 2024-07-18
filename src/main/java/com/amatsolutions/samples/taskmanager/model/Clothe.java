package com.amatsolutions.samples.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clothe_product")
public class Clothe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String title;
    private String price;
    private String rating;
    private String imageUrl;

    // Getters and Setters
}
