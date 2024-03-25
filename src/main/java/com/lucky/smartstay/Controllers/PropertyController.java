package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    public class PropertyController {


    @Autowired
    PropertyService service;
    @GetMapping("property")
public List<Property> listall(){

        System.out.println("\n\n\n\n\n\n\n\n\n\nhello\n\n\n\n\n\n+");
    return service.dispaly();
}
@PostMapping("property")
    public Property addproperty(@RequestBody Property property){
    System.out.println(property);
return service.addProperty(property);
}

    @PutMapping("{propertyId}/details")
    public Property updatePropertyDetails(@PathVariable int userId,@PathVariable int propertyId, @RequestBody PropertyDetails propertyDetails
    ) {
        Property updatedProperty = service.updatePropertyDetails(propertyId, propertyDetails);
        if (updatedProperty != null) {
            return updatedProperty;
        }
        return null;
    }
}
