package com.amatsolutions.samples.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PdfData {

    @Id
    private Long id;
    private String text;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
