package com.example.learn_connection_of_claasses_ford_atabase.controller;

import com.example.learn_connection_of_claasses_ford_atabase.entity.*;
import com.example.learn_connection_of_claasses_ford_atabase.payload.GroupDto;
import com.example.learn_connection_of_claasses_ford_atabase.payload.StudentDto;
import com.example.learn_connection_of_claasses_ford_atabase.repository.AddressRepository;
import com.example.learn_connection_of_claasses_ford_atabase.repository.GroupRepository;
import com.example.learn_connection_of_claasses_ford_atabase.repository.StudentRepository;
import com.example.learn_connection_of_claasses_ford_atabase.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    //    Get students for Ministry
    @GetMapping("/forMinistry")
    public Page<Student> getStudentForMinistry(@RequestParam(required = false) Integer page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> all = studentRepository.findAll(pageable);
        return all;
    }

    //    Get Students for University
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentForUniversity(@PathVariable Integer universityId, @RequestParam(required = false) Integer page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> allByGroupFacultyUniversityId = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return allByGroupFacultyUniversityId;
    }

    //    Get Students for Faculty
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentForFaculty(@PathVariable Integer facultyId, @RequestParam(required = false) Integer page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> allByGroupFacultyId = studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
        return allByGroupFacultyId;
    }

    //    Get Students for Group
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentForGrouyp(@PathVariable Integer groupId, @RequestParam(required = false) Integer page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> allByGroupId = studentRepository.findAllByGroupId(groupId, pageable);
        return allByGroupId;
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddress());
        if (!optionalAddress.isPresent())
            return "Such Address was not found";
        student.setAddress(optionalAddress.get());

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroup());
        if (!optionalAddress.isPresent())
            return "Such group was not found";
        student.setGroup(optionalGroup.get());

        Optional<Subject> optionalSubject = subjectRepository.findById(studentDto.getSubjects());
        if (!optionalSubject.isPresent())
            return "Such subject was not found";
        student.setSubjects(Collections.singletonList(optionalSubject.get()));

        studentRepository.save(student);
        return "Student added";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        try {
            studentRepository.deleteById(id);
            return "Student was deleted";

        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student editStudent = optionalStudent.get();
            editStudent.setFirstName(studentDto.getFirstName());
            editStudent.setLastName(studentDto.getLastName());


            Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddress());
            if (!optionalAddress.isPresent())
                return "Such Address was not found";
            editStudent.setAddress(optionalAddress.get());

            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroup());
            if (!optionalAddress.isPresent())
                return "Such group was not found";
            editStudent.setGroup(optionalGroup.get());

            Optional<Subject> optionalSubject = subjectRepository.findById(studentDto.getSubjects());
            if (!optionalSubject.isPresent())
                return "Such subject was not found";
            editStudent.setSubjects(Collections.singletonList(optionalSubject.get()));


            studentRepository.save(editStudent);
            return "Student edited";
        }
        return "Student was not found";
    }
}
