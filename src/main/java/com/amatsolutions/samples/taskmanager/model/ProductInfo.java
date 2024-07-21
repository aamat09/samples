package com.amatsolutions.samples.taskmanager.model;

import lombok.Data;

@Data
public class ProductInfo {
    private String title;
    private String description;
    private String rating;
    private String imageUrl;
    private String price;

    public ProductInfo(String title, String description, String rating, String imageUrl, String price) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.price = price;
    }


}
