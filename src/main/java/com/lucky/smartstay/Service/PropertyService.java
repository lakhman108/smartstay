package com.lucky.smartstay.Service;


import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Repo.PropertyRepo;
import com.lucky.smartstay.Repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PropertyService {

    @Autowired
    PropertyRepo repo;
    @Autowired
    Userrepo userrepo;


    public List<Property> dispaly() {

       return repo.findAll();
    }

    public Property addProperty(Property property) {

        Optional<User> user=userrepo.findById(property.getUser().getId());
        System.out.println(user);
        property.setUser(user.get());
        property.setPropertyDetails(null);
        return repo.save(property);
    }

    public Property updatePropertyDetails( int propertyId, PropertyDetails propertyDetails) {



        Optional<Property> property = repo.findById(propertyId);
        if (property.isPresent()) {
            Property property1 = property.get();
            property1.setPropertyDetails(propertyDetails);
            return repo.save(property1);
        }
        return null;
    }
}
