package com.example.learn_connection_of_claasses_ford_atabase.repository;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
Page<Student> findAll(Pageable pageable);
Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);
Page<Student> findAllByGroup_FacultyId(Integer group_faculty_id, Pageable pageable);
Page<Student> findAllByGroupId(Integer group_id, Pageable pageable);
}
