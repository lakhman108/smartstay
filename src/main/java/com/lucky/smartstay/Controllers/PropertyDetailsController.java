package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.PropertyDetails;


import com.lucky.smartstay.Service.PropertyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propertyDetails")
public class PropertyDetailsController {

    @Autowired
    private PropertyDetailsService propertyDetailsService;

    @GetMapping({"/{propertyId}"})
    public PropertyDetails getpropretyDetails(@PathVariable int propertyId){

      return   propertyDetailsService.showpropertydetails(propertyId);

    }

    @PostMapping({"/{propertyId}"})
    public PropertyDetails addPropertyDetails(@RequestBody PropertyDetails propertyDetails, @PathVariable int propertyId) {
        return propertyDetailsService.addPropertyDetails(propertyDetails,propertyId);
    }

    @DeleteMapping({"/{propertyId}"})
    public PropertyDetails addPropertyDetails(@PathVariable int propertyId) {
        return propertyDetailsService.deleteProprety(propertyId);
    }

    // Add other propertyDetails-related endpoints as needed
}