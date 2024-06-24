package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    @PreAuthorize("hasAnyRole('DEALER')")
    @PostMapping("")
    public Property addProperty(@RequestBody Property property) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        System.out.println(username);


        int userId = propertyService.getAuthorizedUserId(username);
        System.out.println(userId);
//        return null;
        return propertyService.addProperty(property, userId);
    }


    @PreAuthorize("hasAnyRole('DEALER')")
    @GetMapping()
    public List<Property> getAllProperties() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);


        int userId = propertyService.getAuthorizedUserId(username);
        return propertyService.getAllProperties(userId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEALER','CUSTOMER')")
    @GetMapping("/all")
    public List<Property> getAllPropertiesbyAdmin() {


        return propertyService.allpropreties();
    }

    @PreAuthorize("hasAnyRole('DEALER')")
    @GetMapping("/{n_th_property}")
    public Property getSpecificProprety(@PathVariable int n_th_property) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);


        int userId = propertyService.getAuthorizedUserId(username);
        return propertyService.getNthProprety(n_th_property, userId);
    }

    @PreAuthorize("hasAnyRole('DEALER')")
    @DeleteMapping("/{propertyId}")
    public Property deleteProperty(@PathVariable int propertyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);


        int userId = propertyService.getAuthorizedUserId(username);

        return propertyService.deleteProperty(propertyId, userId);
    }


    // Add other property-related endpoints as needed
}