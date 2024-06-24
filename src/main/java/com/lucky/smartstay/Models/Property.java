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


    @Column(unique = true) // Ensures property_name is unique
    private String property_name;

    @Column(unique = false, nullable = false, length = 100)
    private String owner_name;

    @Column(unique = false, nullable = false, length = 100)
    private String location;

    @Column(unique = false, nullable = false, length = 20)
    private String type;

    @Column(unique = false, nullable = false, length = 20)
    private String status;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)

    private PropertyDetails propertyDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Property{id=" + id + ", name='" + property_name + "', user=" + (user == null ? "null" : "User(" + user.getId() + ")") + "}";
    }
}
