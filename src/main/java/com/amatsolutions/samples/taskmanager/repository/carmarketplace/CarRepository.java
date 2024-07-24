package com.amatsolutions.samples.taskmanager.repository.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByDealerId(Long dealerId);
}
