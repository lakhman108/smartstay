package com.lucky.smartstay.Service;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Repo.PropertyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepo propertyRepository;

    public Property addProperty(Property property, int userId) {
        // Set the user ID for the property
//        property.setUserId(userId);
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Add other property-related methods as needed
}