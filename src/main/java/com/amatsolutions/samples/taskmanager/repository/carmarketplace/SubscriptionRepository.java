package com.amatsolutions.samples.taskmanager.repository.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {}
