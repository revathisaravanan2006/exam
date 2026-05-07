package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
public User updateUser(User user) {

    User existing = userRepo.findById(user.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (user.getName() != null) {
        existing.setName(user.getName());
    }

    if (user.getEmail() != null) {
        existing.setEmail(user.getEmail());
    }

    if (user.getPassword() != null && !user.getPassword().isBlank()) {
        existing.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    if (user.getRole() != null) {
        existing.setRole(user.getRole());
    }

    return userRepo.save(existing);
}

    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}