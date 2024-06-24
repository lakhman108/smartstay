package com.lucky.smartstay.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetails {
    @Id
    @Column(name = "propertyDetails_id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    int id;

    @Column(nullable = false, unique = false)
    int bedrooms;

    @Column(nullable = false, unique = false)
    int bathrooms;
    @Column(nullable = false, unique = false)
    int price;

    @ElementCollection
    @CollectionTable(name = "facility", joinColumns = @JoinColumn(name = "propertyDetails_id"))
    @Column(name = "facility")
    @Enumerated(EnumType.STRING)
    Set<FacilityType> facilities = new HashSet<>();

    public enum FacilityType {
        wifi,
        maid,
        laundry
    }

}