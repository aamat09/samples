package com.amatsolutions.samples.taskmanager.controller.carmaketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.User;
import com.amatsolutions.samples.taskmanager.repository.carmarketplace.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
