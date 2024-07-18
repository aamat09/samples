package com.amatsolutions.samples.taskmanager.repository;

import com.amatsolutions.samples.taskmanager.model.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClotheRepository extends JpaRepository<Clothe, Long> {
    Clothe findByTitle(String title);
}
