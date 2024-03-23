package com.lucky.smartstay.Repo;


import com.lucky.smartstay.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<User,Integer> {
}
