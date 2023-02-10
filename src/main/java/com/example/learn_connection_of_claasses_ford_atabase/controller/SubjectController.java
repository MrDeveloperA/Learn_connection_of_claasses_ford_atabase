package com.example.learn_connection_of_claasses_ford_atabase.controller;

import com.example.learn_connection_of_claasses_ford_atabase.entity.Faculty;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Group;
import com.example.learn_connection_of_claasses_ford_atabase.entity.Subject;
import com.example.learn_connection_of_claasses_ford_atabase.payload.GroupDto;
import com.example.learn_connection_of_claasses_ford_atabase.payload.SubjectDto;
import com.example.learn_connection_of_claasses_ford_atabase.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    //    Create
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exists";

        subjectRepository.save(subject);
        return "Subject added";
    }

    //    Read
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    //  Delete
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        try {
            subjectRepository.deleteById(id);
            return "Subject was deleted";

        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    //    Update
    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody SubjectDto subjectDto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject editSubject = optionalSubject.get();
            editSubject.setName(subjectDto.getName());

            subjectRepository.save(editSubject);
            return "Subject edited";
        }
        return "Subject was not found";
    }
}
