package com.lucky.smartstay.Controller;

import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.PropertyDetails;
import com.lucky.smartstay.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class PropertyController {


    @Autowired
    PropertyService service;
    @GetMapping("display")
public List<Property> listall(){
    return service.dispaly();
}

}
