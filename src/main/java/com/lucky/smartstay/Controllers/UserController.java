package com.lucky.smartstay.Controllers;


import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserService service;


    @PostMapping("register")
    public User register(@RequestBody User user) {

        return service.save(user);
    }

    @GetMapping("users")
    public List<User> showuserdeatils() {
        List<User> users = service.display();
        for (int i = 0; i < users.toArray().length; i++) {
            System.out.println(users.get(i));
        }
        return users;
    }
}
