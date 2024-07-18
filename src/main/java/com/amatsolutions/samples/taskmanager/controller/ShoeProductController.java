package com.amatsolutions.samples.taskmanager.controller;

import com.amatsolutions.samples.taskmanager.model.NikeShoeProduct;
import com.amatsolutions.samples.taskmanager.repository.NikeShoeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoeProductController {

    @Autowired
    private NikeShoeProductRepository shoeProductRepository;

    @GetMapping("/api/shoe-products")
    public List<NikeShoeProduct> getShoeProducts() {
        return shoeProductRepository.findAll();
    }
}
