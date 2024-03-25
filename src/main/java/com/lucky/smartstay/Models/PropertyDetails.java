package com.lucky.smartstay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetails {
    @Id
    @Column(name = "propertyDetails_id")

    int id;

    int bedrooms;

    int bathrooms;

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
    @JsonIgnore
    @OneToOne(mappedBy = "propertyDetails")
    private Property property;
}