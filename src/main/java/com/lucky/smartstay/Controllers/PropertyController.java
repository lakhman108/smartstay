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

    @PostMapping("/{userId}")
    public Property addProperty(@RequestBody Property property, @PathVariable int userId) {
        System.out.println("\n\n\n\n\n\n"+property);
        System.out.println("\n\n\n\n\n\n"+userId);
        return propertyService.addProperty(property, userId);
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Add other property-related endpoints as needed
}