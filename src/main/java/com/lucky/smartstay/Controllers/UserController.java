package com.lucky.smartstay.Controllers;

import com.lucky.smartstay.Exceptions.ResourceNotFoundException;
import com.lucky.smartstay.Exceptions.UserNotFoundException;
import com.lucky.smartstay.Models.Property;
import com.lucky.smartstay.Models.Role;
import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Models.UserDto;
import com.lucky.smartstay.Repo.Userrepo;
import com.lucky.smartstay.Service.PropertyService;
import com.lucky.smartstay.Service.UserService;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    PropertyService propertyService;
    @Autowired
    private UserService userService;
    @Autowired
    private Userrepo userrepo;

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {

        System.out.println(user.getPhoneNo());
        user.setPhoneNo(user.getPhoneNo().replace("-",""));
        if (user.getRole() == Role.ADMIN) {
            user.setRole(Role.CUSTOMER);
        }


        return userService.addUser(user);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    public List<User> listUsers() {

        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping({"/all"})
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = userrepo.findAllAsDto();
        List<User> users = userrepo.findAll();

        // Iterate over the users and initialize the properties collection
        for (User user : users) {
            Hibernate.initialize(user.getProperties());
        }

        // Iterate over the users and set the properties in the corresponding DTO
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserDto userDto = userDtos.get(i);
            userDto.setProperties(user.getProperties());
        }

        return userDtos;
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{User_id}")
    public User deleteuser(@PathVariable int User_id) {
        System.out.println(User_id);
        return userService.deleteuser(User_id);

    }

    @PreAuthorize("hasAnyRole('CUSTOMER','DEALER')")
    @DeleteMapping("")
    public User deletItself() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);

        int userId = propertyService.getAuthorizedUserId(username);
        return userService.deleteuser(userId);

    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping("/bookmark/{id}")
    public Property addBookmark(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);

        int userId = propertyService.getAuthorizedUserId(username);
        System.out.println(userId);
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<Property> bookmarks = user.getBookmarks();
            Optional<Property> property = propertyService.getThisPro(id);
            if (property.isPresent()) {
                Property pro = property.get();
                user.bookProperty(pro);
                userrepo.save(user);
                return pro;
            } else {
                throw new ResourceNotFoundException("Property with id " + id + " not found");
            }
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

    }

    //bookmarks get
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/bookmark")
    Set<Property> getBookmarks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the logged-in user's username
        String username = authentication.getName();
        System.out.println(username);

        int userId = propertyService.getAuthorizedUserId(username);
        System.out.println(userId);
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getBookmarks();
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }

    //bookmarks post ,edit ,delete
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @DeleteMapping("/bookmark/{book_id}")
    public Property deleteBookmark(@PathVariable int book_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();
        System.out.println(username);


        int userId = propertyService.getAuthorizedUserId(username);
        System.out.println(userId);
        Optional<User> optionalUser = userrepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<Property> bookmarks = user.getBookmarks();
            Optional<Property> property = propertyService.getThisPro(book_id);
            if (property.isPresent()) {
                Property pro = property.get();
                user.debookProperty(pro);
                userrepo.save(user);
                return pro;
            } else {
                return null;
            }
        } else {
            return null;
        }


    }

    Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // Add other user-related endpoints as needed
    //bookmarks get
    //bookmarks post ,edit ,delete


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

}