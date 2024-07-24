package com.amatsolutions.samples.taskmanager.service.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Car;
import com.amatsolutions.samples.taskmanager.repository.carmarketplace.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getDealerInventory(Long dealerId) {
        return carRepository.findByDealerId(dealerId);
    }

    public List<Car> getDealerSales(Long dealerId) {
        // For simplicity, we assume all cars in inventory are sold
        // In a real scenario, you would have a separate table for sales transactions
        return carRepository.findByDealerId(dealerId);
    }
}
