package com.lucky.smartstay.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot be longer than 100 characters")
    @Column(nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot be longer than 100 characters")
    @Column(nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username cannot be longer than 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 10, message = "Phone number cannot be longer than 10 digits")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    @Column(nullable = false, unique = true, length = 10)
    private String phoneNo;

    @NotBlank(message = "Password is required")
    @Column(nullable = false, length = 100)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_property",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> bookmarks = new HashSet<>();

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + firstName + lastName + " " + "', properties=" + properties.stream().map(p -> "Property(" + p.getId() + ")").collect(Collectors.joining(", ")) + "}";
    }

    public void bookProperty(Property property) {
        bookmarks.add(property);
    }

    public void debookProperty(Property property) {
        bookmarks.remove(property);
    }


}