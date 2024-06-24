package com.lucky.smartstay.Repo;


import com.lucky.smartstay.Models.User;
import com.lucky.smartstay.Models.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Userrepo extends JpaRepository<User, Integer> {
    @Query("SELECT new com.lucky.smartstay.Models.UserDto(u.id, u.firstName, u.lastName, u.email, u.phoneNo, u.role, null) FROM User u")
    List<UserDto> findAllAsDto();


    User findByUsername(String username);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhoneNo(String phoneNo);
}
