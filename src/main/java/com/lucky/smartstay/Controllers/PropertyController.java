package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.Property;

import com.lucky.smartstay.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    @GetMapping()
    public List<Property> getAllProperties() {

        int userId = 1;
        return propertyService.getAllProperties(userId);
    }
    @GetMapping("/{n_th_property}")
    public Property getSpecificProprety(@PathVariable int n_th_property) {
        int UserId = 1;
        return propertyService.getNthProprety(n_th_property,UserId);
    }
    @PostMapping("/{userId}")
    public Property addProperty(@RequestBody Property property, @PathVariable int userId) {

        return propertyService.addProperty(property, userId);
    }

    @DeleteMapping("/{propertyId}/{userId}")
    public Property deleteProperty(@PathVariable int propertyId, @PathVariable int userId) {
        System.out.println("Property Id: " + propertyId);
        System.out.println("User Id: " + userId);

        return propertyService.deleteProperty(propertyId, userId);
    }



    // Add other property-related endpoints as needed
}