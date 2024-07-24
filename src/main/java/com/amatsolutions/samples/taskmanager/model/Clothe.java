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
    private String description;

    public Clothe(String title, String description, String rating, String imageUrl, String price) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.price = price;
        this.type = "clothe";
    }

    public Clothe() {
    }

    // Getters and Setters
}
