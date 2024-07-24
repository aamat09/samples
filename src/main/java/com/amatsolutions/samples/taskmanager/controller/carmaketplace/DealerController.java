package com.amatsolutions.samples.taskmanager.controller.carmaketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Car;
import com.amatsolutions.samples.taskmanager.service.carmarketplace.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin(origins = "*")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @GetMapping("/{dealerId}/inventory")
    public List<Car> getDealerInventory(@PathVariable Long dealerId) {
        return dealerService.getDealerInventory(dealerId);
    }

    @GetMapping("/{dealerId}/sales")
    public List<Car> getDealerSales(@PathVariable Long dealerId) {
        return dealerService.getDealerSales(dealerId);
    }
}
