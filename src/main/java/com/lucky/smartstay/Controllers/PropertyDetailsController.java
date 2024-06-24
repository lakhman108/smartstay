package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Service.PropertyDetailsService;
import com.lucky.smartstay.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propertyDetails")
public class PropertyDetailsController {

    @Autowired
    private PropertyDetailsService propertyDetailsService;
    @Autowired
    private PropertyService propertyService;

    @PreAuthorize("hasAnyRole('DEALER','ADMIN','CUSTOMER')")
    @GetMapping({""})
    public List<Property> getAllProperties() {
        return propertyService.getAllPro();
    }


    @PreAuthorize("hasAnyRole('CUSTOMER','DEALER','ADMIN')")
    @GetMapping({"/{propertyId}"})
    public PropertyDetails getpropretyDetails(@PathVariable int propertyId) {

        return propertyDetailsService.showpropertydetails(propertyId);

    }

    @PreAuthorize("hasAnyRole('DEALER','ADMIN')")
    @PostMapping({"/{propertyId}"})
    public PropertyDetails addPropertyDetails(@RequestBody PropertyDetails propertyDetails, @PathVariable int propertyId) {
        return propertyDetailsService.addPropertyDetails(propertyDetails, propertyId);
    }

    @PreAuthorize("hasAnyRole('DEALER','ADMIN')")
    @DeleteMapping({"/{propertyId}"})
    public PropertyDetails addPropertyDetails(@PathVariable int propertyId) {
        return propertyDetailsService.deleteProprety(propertyId);
    }

    // Add other propertyDetails-related endpoints as needed
}