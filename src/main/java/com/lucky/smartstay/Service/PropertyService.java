package com.lucky.smartstay.Service;


import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Repo.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropertyService {

    @Autowired
    PropertyRepo repo;


    public List<Property> dispaly() {

       return repo.findAll();
    }
}
