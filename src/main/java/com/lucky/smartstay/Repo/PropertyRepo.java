package com.lucky.smartstay.Repo;

import com.lucky.smartstay.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepo extends JpaRepository<Property,Integer> {


}
