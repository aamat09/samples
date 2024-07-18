package com.amatsolutions.samples.taskmanager.repository;

import com.amatsolutions.samples.taskmanager.model.NikeShoeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NikeShoeProductRepository extends JpaRepository<NikeShoeProduct, Long> {
        NikeShoeProduct findByTitle(String title);
}
