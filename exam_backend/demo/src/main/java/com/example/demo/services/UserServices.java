package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepo userRepo;

    public UserServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    public User updateUser(User user) {
        if (!userRepo.existsById(user.getUserId())) {
            throw new IllegalArgumentException("User not found: " + user.getUserId());
        }
        return userRepo.save(user);
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }
}
