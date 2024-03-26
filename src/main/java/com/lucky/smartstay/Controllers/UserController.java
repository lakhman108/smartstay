package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.Role;
import com.lucky.smartstay.Models.User;

import com.lucky.smartstay.Models.UserDto;
import com.lucky.smartstay.Repo.Userrepo;
import com.lucky.smartstay.Service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Userrepo userrepo;
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping()
    public List<User> listUsers() {

        return userService.getAllUsers();
    }

    @GetMapping({"/all"})
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = userrepo.findAllAsDto();
        List<User> users = userrepo.findAll();

        // Iterate over the users and initialize the properties collection
        for (User user : users) {
            Hibernate.initialize(user.getProperties());
        }

        // Iterate over the users and set the properties in the corresponding DTO
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDto userDto = userDtos.get(i);
            userDto.setProperties(user.getProperties());
        }

        return userDtos;
    }

    @DeleteMapping("/{User_id}")
    public User deleteuser(@PathVariable int User_id){
        return userService.deleteuser(User_id);

}

    // Add other user-related endpoints as needed
}