package com.lucky.smartstay.Service;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Repo.PropertyDetailsRepository;
import com.lucky.smartstay.Repo.PropertyRepo;

import com.lucky.smartstay.Repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepo propertyRepository;

    @Autowired
    private Userrepo userRepository;


    @Autowired
    private PropertyDetailsRepository propertyDetailsRepository;

    public List<Property> getAllProperties(int userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(User::getProperties).orElse(null);
    }

    public Property getNthProprety(int nThProperty,int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User currentUser = user.get();
            List<Property> properties = currentUser.getProperties();
            if (nThProperty >= 0 && nThProperty < properties.size()) {
                return properties.get(nThProperty);
            }
        }
        return null;
    }

    public Property addProperty(Property property, int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Fetch the PropertyDetails entity from the database
            PropertyDetails propertyDetails = propertyDetailsRepository.findById(property.getPropertyDetails().getId()).orElse(null);

            // Set the fetched PropertyDetails to the Property
            property.setPropertyDetails(propertyDetails);

            // Set the user for the property
            property.setUser(user);

            return propertyRepository.save(property);
        } else {
            // Handle case when user is not found
            return null;
        }
    }

    public Property deleteProperty(Integer propertyIndex, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent())
        System.out.println(userOptional.get());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Property> properties = user.getProperties();
            System.out.println("Property Index: " +properties);

            if (propertyIndex >= 0 && propertyIndex < properties.size()) {
                Property property = properties.get(propertyIndex);
                properties.remove(propertyIndex.intValue());
                propertyRepository.delete(property);
                userRepository.save(user); // Save the user after removing the property
            }
        }
        return null;
    }

    public int getAuthorizedUserId(String username) {

        User user=userRepository.findByLastName(username);
        return user.getId();
    }


    // Add other property-related methods as needed
}