package com.lucky.smartstay.Service;


import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

@Autowired
Userrepo repo;
    public User save(User user) {
        return repo.save(user);
    }

    public List<User> display() {

        return repo.findAll();
    }
}
