package com.lucky.smartstay.Service;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Repo.PropertyDetailsRepository;
import com.lucky.smartstay.Repo.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PropertyDetailsService {

    @Autowired
    private PropertyDetailsRepository propertyDetailsRepository;

    @Autowired
    private PropertyRepo repo;

    @Autowired
    private PropertyRepo propertyRepo;


    public PropertyDetails showpropertydetails(int propertyId) {

        Optional<Property> property = propertyRepo.findById(propertyId);
        if (property.isPresent())
            return property.get().getPropertyDetails();
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property with ID " + propertyId + " not found");
        }
    }

    public PropertyDetails addPropertyDetails(PropertyDetails propertyDetails, int propertyId) {
        Optional<Property> property = propertyRepo.findById(propertyId);

        if (property.isPresent()) {
            property.get().setPropertyDetails(propertyDetails);
            Property updatedProperty = propertyRepo.save(property.get());
            return updatedProperty.getPropertyDetails();
        }
        return new PropertyDetails();
    }


    public PropertyDetails deleteProprety(int propertyId) {

        Optional<Property> property = propertyRepo.findById(propertyId);
        if (property.isPresent()) {
            System.out.println(property.get().getPropertyDetails());
            property.get().setPropertyDetails(null);
            propertyRepo.save(property.get());
            return null;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property with ID " + propertyId + " not found");


    }

}