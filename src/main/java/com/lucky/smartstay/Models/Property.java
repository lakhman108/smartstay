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
    int id;


    String property_name;

    String owner_name;

    String location;

    String type;

    String status;

    @ManyToOne()
    @JoinColumn(name = "user_id")

    User user;

    @OneToOne

    PropertyDetails propertyDetails;


}
