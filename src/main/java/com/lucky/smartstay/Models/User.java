package com.lucky.smartstay.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String username;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false,length = 100)
    private String password;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_property",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> bookmarks=new HashSet<>();

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + firstName + lastName + " "+ "', properties=" + properties.stream().map(p -> "Property(" + p.getId() + ")").collect(Collectors.joining(", ")) + "}";
    }
    public void bookProperty(Property property){
        bookmarks.add(property);
    }

    public void debookProperty(Property property){
        bookmarks.remove(property);
    }


}