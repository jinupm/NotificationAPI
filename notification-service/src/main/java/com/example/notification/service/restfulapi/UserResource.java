package com.example.notification.service.restfulapi;

import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {
    private UserDaoService service;
    private MessageSource messageSource;

    public UserResource(UserDaoService service,MessageSource messageSource){
        this.service = service;
        this.messageSource=messageSource;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.getUsers();
    }
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable("id") int id){
        User user = service.findOne(id);
        if(user == null){
                throw new UserNotFoundException("id:" +id );
        }
        return user;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") int id){
        service.deleteUserById(id);
    }

    @GetMapping(path = "/hello-world-internalized")
    public String helloWorldInternalize(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }
}
