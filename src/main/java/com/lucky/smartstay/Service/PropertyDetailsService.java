package com.lucky.smartstay.Services;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;

import com.lucky.smartstay.Repo.PropertyDetailsRepository;
import com.lucky.smartstay.Repo.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyDetailsService {

    @Autowired
    private PropertyDetailsRepository propertyDetailsRepository;

    @Autowired
    private PropertyRepo repo;

    public PropertyDetails addPropertyDetails(PropertyDetails propertyDetails,int propertyId) {
        Property property=new Property();
        property.setPropertyDetails(propertyDetails);
        return propertyDetailsRepository.save(propertyDetails);
    }

    // Add other propertyDetails-related methods as needed
}