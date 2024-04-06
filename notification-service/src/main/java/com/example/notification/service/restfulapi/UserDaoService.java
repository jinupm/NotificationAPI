package com.example.notification.service.restfulapi;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Goku", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Gohan", LocalDate.now().minusYears(12)));
        users.add(new User(++userCount,"Vegeta", LocalDate.now().minusYears(40)));
    }

    public  List<User> getUsers() {
        return users;
    }
    public  User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }
    public  void deleteUserById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
