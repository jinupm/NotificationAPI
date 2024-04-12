package com.example.notificationservice.restfulapi.service;


import com.example.notificationservice.restfulapi.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String userId);
}
