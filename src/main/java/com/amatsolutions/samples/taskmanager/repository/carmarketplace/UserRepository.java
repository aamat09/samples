package com.amatsolutions.samples.taskmanager.repository.carmarketplace;


import com.amatsolutions.samples.taskmanager.model.carmarketplace.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}

