package com.lucky.smartstay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    User user;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private PropertyDetails propertyDetails;

}
