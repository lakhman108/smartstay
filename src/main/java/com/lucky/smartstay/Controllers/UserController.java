package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.User;

import com.lucky.smartstay.Models.UserDto;
import com.lucky.smartstay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Get all users");
        System.out.println(userService.getUsersWithProperties());
        System.out.println("\n\n\n\n\n\n\n\n");
        return userService.getAllUsers();
    }

    // Add other user-related endpoints as needed
}