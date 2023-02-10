package com.example.learn_connection_of_claasses_ford_atabase.repository;

import com.example.learn_connection_of_claasses_ford_atabase.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
