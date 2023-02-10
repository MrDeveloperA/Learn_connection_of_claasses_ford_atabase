package com.example.learn_connection_of_claasses_ford_atabase.repository;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
