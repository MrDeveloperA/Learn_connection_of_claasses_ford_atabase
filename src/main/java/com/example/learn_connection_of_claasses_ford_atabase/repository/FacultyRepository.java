package com.example.learn_connection_of_claasses_ford_atabase.repository;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
boolean existsByNameAndUniversityId(String name, Integer university_id);
List<Faculty> findAllByUniversityId(Integer university_id);
}
