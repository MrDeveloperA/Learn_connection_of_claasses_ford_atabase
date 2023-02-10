package com.example.learn_connection_of_claasses_ford_atabase.repository;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
boolean existsByName(String name);
}
