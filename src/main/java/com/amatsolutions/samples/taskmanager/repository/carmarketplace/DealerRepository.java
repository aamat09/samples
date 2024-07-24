package com.amatsolutions.samples.taskmanager.repository.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
