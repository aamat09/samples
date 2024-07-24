package com.amatsolutions.samples.taskmanager.service.carmarketplace;


import com.amatsolutions.samples.taskmanager.model.carmarketplace.Car;
import com.amatsolutions.samples.taskmanager.repository.carmarketplace.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

// Similar services can be created for User, Dealer, Favorite, and Subscription
