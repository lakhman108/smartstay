package com.lucky.smartstay.Models;

import lombok.Data;

import java.util.List;

@Data


public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private Role role;
    private List<Property> properties;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, String email, String phoneNo, Role role, List<Property> properties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.role = role;
        this.properties = properties;
    }
// Constructor matching the fields


    // Getters and setters
}