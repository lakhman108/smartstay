package com.lucky.smartstay.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;


    String property_name;

    String owner_name;

    String location;

    String type;

    String status;



    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)

    private PropertyDetails propertyDetails;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Override
    public String toString() {
        return "Property{id=" + id + ", name='" + property_name + "', user=" + (user == null ? "null" : "User(" + user.getId() + ")") + "}";
    }
}
