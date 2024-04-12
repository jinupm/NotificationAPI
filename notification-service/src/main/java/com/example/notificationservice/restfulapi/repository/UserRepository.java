package com.example.notificationservice.restfulapi.repository;

import com.example.notificationservice.restfulapi.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
