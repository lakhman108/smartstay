package com.lucky.smartstay.Service;

import com.lucky.smartstay.Exceptions.UserNotFoundException;
import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Repo.PropertyDetailsRepository;
import com.lucky.smartstay.Repo.PropertyRepo;
import com.lucky.smartstay.Repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Property getNthProprety(int nThProperty, int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User currentUser = user.get();
            List<Property> properties = currentUser.getProperties();
            if (nThProperty >= 0 && nThProperty < properties.size()) {
                return properties.get(nThProperty);
            } else {
                // Throw custom exception when property index is out of bounds
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
            }
        } else {
            // Throw custom exception when user is not found
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
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
            // Throw custom exception when user is not found
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }

    public Property deleteProperty(Integer propertyIndex, Integer userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<Property> properties = user.getProperties();

                if (propertyIndex >= 0 && propertyIndex < properties.size()) {
                    Property property = properties.get(propertyIndex);
                    properties.remove(propertyIndex.intValue());
                    propertyRepository.delete(property);
                    userRepository.save(user); // Save the user after removing the property
                    return property;
                } else {
                    throw new IllegalArgumentException("Invalid property index: " + propertyIndex);
                }
            } else {
                throw new UserNotFoundException("User with id " + userId + " not found");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid property index: " + propertyIndex, e);
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User with id " + userId + " not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete property", e);
        }
    }


    public int getAuthorizedUserId(String username) {

        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return user.getId();
            } else {
                throw new UserNotFoundException("User with username " + username + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve authorized user ID for username: " + username, e);
        }
    }


    public List<Property> getAllPro() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getThisPro(int id) {
        return propertyRepository.findById(id);
    }

    public List<Property> allpropreties() {
        return propertyRepository.findAll();
    }
    // Add other property-related methods as needed
}